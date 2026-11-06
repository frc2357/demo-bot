package frc.robot.controls;

import static edu.wpi.first.units.Units.Value;

import edu.wpi.first.units.measure.Dimensionless;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.ControllerConstants;

public class DriverControls {
    private final CommandXboxController controller;

    public DriverControls() {
        controller = new CommandXboxController(ControllerConstants.kDriverControllerPort);
    }

    public Dimensionless getLeftForwardInput() {
        return Value.of(-controller.getLeftY());
    }

    public Dimensionless getLeftRotationZInput() {
        return Value.of(controller.getRightX());
    }

    public Dimensionless getRightTriggerAxis() {
        return Value.of(controller.getRightTriggerAxis());
    }
}