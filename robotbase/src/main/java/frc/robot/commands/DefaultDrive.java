package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.units.measure.Dimensionless;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;

public class DefaultDrive extends Command {
    private final Dimensionless m_speed;
    private final Supplier<Dimensionless> m_forward;
    private final Supplier<Dimensionless> m_rotation;

    public DefaultDrive(Dimensionless speed, Supplier<Dimensionless> forward, Supplier<Dimensionless> rotation) {
        addRequirements(Robot.driveSubsystem);
        m_speed = speed;
        m_forward = forward;
        m_rotation = rotation;
    }

    @Override
    public void initialize() {
        SmartDashboard.putBoolean("TeleOp", true);
    }

    @Override
    public void execute() {
        System.out.println("EXECUTING");
        Robot.driveSubsystem.differentialDrive(m_speed, m_forward.get(), m_rotation.get());
    }

    @Override
    public boolean isFinished() {
        return !Robot.currentInstance.isTeleop();
    }

    @Override
    public void end(boolean interrupted) {
        SmartDashboard.putBoolean("TeleOp", false);
        Robot.driveSubsystem.stop();
    }
}
