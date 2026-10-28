package frc.robot.commands.drive;

import edu.wpi.first.units.measure.Dimensionless;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;

public class DifferentialDrive extends Command {
    private final Dimensionless m_speed;
    private final Dimensionless m_forward;
    private final Dimensionless m_rotation;

    public DifferentialDrive(Dimensionless speed, Dimensionless forward, Dimensionless rotation) {
        addRequirements(Robot.driveSubsystem);
        m_speed = speed;
        m_forward = forward;
        m_rotation = rotation;
    }

    @Override
    public void execute() {
        Robot.driveSubsystem.differentialDrive(m_speed, m_forward, m_rotation);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        Robot.driveSubsystem.stop();
    }
}
