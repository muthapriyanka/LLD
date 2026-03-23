package amazonlocker;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LockerService {
    private final Locker locker;
    private final Map<String, AccessToken> tokenMap = new HashMap<>();

    public LockerService(Locker locker) {
        this.locker = locker;
    }

    public String depositPackage(PackageItem packageItem) {
        Compartment compartment = findBestFitCompartment(packageItem.getSize());

        if (compartment == null) {
            throw new RuntimeException("No available compartment for size: " + packageItem.getSize());
        }

        compartment.open();
        compartment.assignPackage(packageItem);

        String tokenCode = UUID.randomUUID().toString();

        AccessToken token = new AccessToken(
                tokenCode,
                Instant.now().plus(3, ChronoUnit.DAYS),
                compartment
        );

        tokenMap.put(tokenCode, token);

        System.out.println("Deposited package " + packageItem.getPackageId() +
                " in compartment " + compartment.getCompartmentId());
        System.out.println("Generated token: " + tokenCode);

        return tokenCode;
    }

    public void pickupPackage(String tokenCode) {
        AccessToken token = tokenMap.get(tokenCode);

        if (token == null) {
            throw new RuntimeException("Invalid token");
        }

        if (token.isExpired()) {
            throw new RuntimeException("Token expired");
        }

        Compartment compartment = token.getCompartment();
        compartment.open();

        PackageItem pkg = compartment.getCurrentPackage();
        System.out.println("Picked up package: " +
                (pkg != null ? pkg.getPackageId() : "UNKNOWN"));

        compartment.removePackage();
        tokenMap.remove(tokenCode);
    }

    private Compartment findBestFitCompartment(Size packageSize) {
        Compartment bestFit = null;

        for (Compartment compartment : locker.getCompartments()) {
            if (compartment.isOccupied()) continue;

            if (compartment.getSize().ordinal() >= packageSize.ordinal()) {
                if (bestFit == null ||
                        compartment.getSize().ordinal() < bestFit.getSize().ordinal()) {
                    bestFit = compartment;
                }
            }
        }

        return bestFit;
    }
}
