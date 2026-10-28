package frc.robot.controls;

import static edu.wpi.first.units.Units.Value;

import edu.wpi.first.units.measure.Dimensionless;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.ControllerConstants;
import frc.robot.commands.drive.SetDriveSpeed;

public class DriverControls {
    private final CommandXboxController controller;

    public DriverControls() {
        controller = new CommandXboxController(ControllerConstants.kDriverControllerPort);

        // controller.b().whileTrue(new SetDriveSpeed(Value.of(1)));
    }

    public Dimensionless getForwardInput() {
        return Value.of(-controller.getLeftY());
    }

    public Dimensionless getRotationZInput() {
        return Value.of(controller.getLeftX());
    }
}