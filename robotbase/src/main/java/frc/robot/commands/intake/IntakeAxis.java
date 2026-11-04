package frc.robot.commands.intake;

import java.util.function.Supplier;

import edu.wpi.first.units.measure.Dimensionless;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;
import frc.robot.Constants.IntakeConstants;

public class IntakeAxis extends Command {
    Supplier<Dimensionless> m_axis;

    public IntakeAxis(Supplier<Dimensionless> axis) {
        addRequirements(Robot.intakeSubsystem);
        m_axis = axis;
    }

    @Override
    public void execute() {
        Robot.intakeSubsystem.setSpeed(m_axis.get().times(IntakeConstants.kAxisMaxSpeed));
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        Robot.intakeSubsystem.stop();
    }
}
