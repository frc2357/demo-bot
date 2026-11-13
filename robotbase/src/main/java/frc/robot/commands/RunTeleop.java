package frc.robot.commands;

import static edu.wpi.first.units.Units.Value;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Robot;
import frc.robot.Constants.ControllerConstants;
import frc.robot.commands.drive.DefaultDrive;
import frc.robot.commands.intake.IntakeAxis;

public class RunTeleop extends ParallelCommandGroup {
    public RunTeleop() {
        super(new DefaultDrive(
                () -> Value.of(
                        SmartDashboard.getNumber("Driving Speed", ControllerConstants.kDefaultDriverSpeed.in(Value))).times(Robot.driverControls.getLimiterSpeedMultiplier()),
                Robot.driverControls::getDriveForwardInput,
                Robot.driverControls::getDriveRotationZInput),
            new IntakeAxis(Robot.driverControls::getIntakeAxis));
    }
}
