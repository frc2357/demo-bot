package frc.robot.commands.drive;

import static edu.wpi.first.units.Units.Value;

import edu.wpi.first.units.measure.Dimensionless;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.DriveSubsystem;

public class ApplyBrake extends Command {
    private Dimensionless m_brakeDeceleration;
    private long lastTime;

    public ApplyBrake(Dimensionless brakeDeceleration) {
        addRequirements(Robot.driveSubsystem);
        m_brakeDeceleration = brakeDeceleration;
    }

    @Override
    public void initialize() {
        lastTime = System.nanoTime();
    }

    @Override
    public void execute() {
        long current = System.nanoTime();
        double deltaTime = (current - lastTime) / 1_000_000_000d;
        DriveSubsystem drive = Robot.driveSubsystem;
        double speed = drive.getAverageSpeed();
        
        drive.setSpeed((speed - (Math.signum(speed) * m_brakeDeceleration.in(Value) * deltaTime)));
    }

    @Override
    public boolean isFinished() {
        return Robot.driveSubsystem.getAverageSpeed() == 0;
    }

    @Override
    public void end(boolean interrupted) {
        Robot.driveSubsystem.stop();
    }
}
