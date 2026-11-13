package frc.robot.controls;

import static edu.wpi.first.units.Units.Value;

import edu.wpi.first.units.measure.Dimensionless;
import edu.wpi.first.units.measure.MutDimensionless;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.ControllerConstants;

public class DriverControls {
    private final CommandXboxController controller;
    private MutDimensionless newDriveSpeed = new MutDimensionless(0, 0, Value); 

    public DriverControls() {
        controller = new CommandXboxController(ControllerConstants.kDriverControllerPort);

        controller.b().whileTrue(new RunCommand(() -> {
            newDriveSpeed.mut_setMagnitude(1d - Math.pow(controller.getLeftTriggerAxis(), 0.5));
        }));
    }

    public Dimensionless getDriveForwardInput() {
        return Value.of(-controller.getLeftY());
    }

    public Dimensionless getDriveRotationZInput() {
        return Value.of(controller.getRightX());
    }

    public Dimensionless getIntakeAxis() {
        return Value.of(controller.getRightTriggerAxis());
    }

    public Dimensionless getLimiterSpeedMultiplier() {
        return newDriveSpeed.copy();
    }
}