package frc.robot.subsystems;

import static edu.wpi.first.units.Units.RotationsPerSecond;
import static edu.wpi.first.units.Units.Value;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Dimensionless;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

/** The subsystem representing the robot's ability to move around. */
public class DriveSubsystem extends SubsystemBase {
    // The motor on the left side of the drive.
    private final TalonFX leftMotor = new TalonFX(DriveConstants.kLeftMotorPort);

    // The motor on the right side of the drive.
    private final TalonFX rightMotor = new TalonFX(DriveConstants.kRightMotorPort);

    /** Creates a new DriveSubsystem. */
    public DriveSubsystem() {
        SendableRegistry.addChild(this, leftMotor);
        SendableRegistry.addChild(this, rightMotor);
        coast();
    }

    /** 
     * Allows the drive to coast when there is no speed. 
     */
    public void coast() {
        leftMotor.setNeutralMode(NeutralModeValue.Coast);
        rightMotor.setNeutralMode(NeutralModeValue.Coast);
    }

    /**
     * Returns the current set speed of the left motor.
     * 
     * @return The current set speed of the left motor.
     */
    public double getLeftSpeed() {
        return leftMotor.get();
    }

    /**
     * Returns the current speed of the left motor in rotations per second.
     * 
     * @return The current speed of the left motor in rotations per second.
     */
    public AngularVelocity getLeftCurrentVelocity() {
        return RotationsPerSecond.of(getLeftSpeed() * getCurrentLeftRps());
    }

    /**
     * Returns the current speed of the right motor.
     * 
     * @return The current speed of the right motor.
     */
    public double getRightSpeed() {
        return rightMotor.get();
    }

    /**
     * Returns the current speed of the left motor in rotations per second.
     * 
     * @return The current speed of the left motor in rotations per second.
     */
    public AngularVelocity getRightCurrentVelocity() {
        return RotationsPerSecond.of(getRightSpeed() * getCurrentRightRps());
    }

    /**
     * Returns the average set speed of the drive.
     * 
     * @return The average set speed of both motors.
     */
    public double getAverageSpeed() {
        return (getLeftSpeed() + getRightSpeed()) / 2;
    }

    /**
     * Returns the current speed of the left motor in rotations per second.
     * 
     * @return The current speed of the left motor in rotations per second.
     */
    public AngularVelocity getAverageCurrentVelocity() {
        return RotationsPerSecond.of(getAverageSpeed() * (getCurrentLeftRps() + getCurrentRightRps()) / 2);
    }

    /**
     * Sets the max output of the drive. Useful for scaling the drive to drive more
     * slowly.
     *
     * @param speed the maximum output to which the drive will be constrained
     */
    public void setSpeed(double speed) {
        leftMotor.set(speed);
        rightMotor.set(speed);
    }

    /**
     * Sets the max output of the drive using a dimensionless value. Useful for scaling the drive to drive more
     * slowly.
     *
     * @param speed the maximum output to which the drive will be constrained
     */
    public void setSpeed(Dimensionless speed) {
        setSpeed(speed.in(Value));
    }

    /**
     * Returns the current RPS of the left motor according to its current voltage supply. Meant to be interally used to achieve accurate velocity.
     * 
     * @return The left motor's rotations per second.
     */
    private double getCurrentLeftRps() {
        return leftMotor.getMotorKV().getValueAsDouble() * leftMotor.getSupplyVoltage().getValueAsDouble() * 60;
    }

    /**
     * Returns the current RPS of the right motor according to its current voltage supply. Meant to be interally used to achieve accurate velocity.
     * 
     * @return The right motor's rotations per second.
     */
    private double getCurrentRightRps() {
        return rightMotor.getMotorKV().getValueAsDouble() * rightMotor.getSupplyVoltage().getValueAsDouble() * 60;
    }

    /**
     * Sets the output of the drive using linear velocity. Useful for scaling the drive to drive more
     * slowly.
     *
     * @param speed the output in linear velocity to which the drive will target.
     */
    public void setSpeed(LinearVelocity speed) {
        double desiredRps = speed.div(DriveConstants.kWheelDistancePerSecondPerRps).in(Value);
        leftMotor.set(desiredRps / getCurrentLeftRps());
        rightMotor.set(desiredRps / getCurrentRightRps());
    }

    /**
     * Sets the output of the drive using angular velocity. Useful for scaling the drive to drive more
     * slowly.
     *
     * @param speed the output in angular velocity to which the drive will target.
     */
    public void setSpeed(AngularVelocity speed) {
        double desiredRps = speed.div(DriveConstants.kWheelAngularVelocityPerRps).in(Value);
        leftMotor.set(desiredRps / getCurrentLeftRps());
        rightMotor.set(desiredRps / getCurrentRightRps());
    }

    /**
     * Unlike {@link DriveSubsystem#differentialDrive(double, double, double)}, the left and right motors can be set individually.
     * 
     * @param leftSpeed The maximum output of the left motor.
     * @param rightSpeed The maximum output of the right motor.
     * @param forward The forward input to use.
     * @param rotation The rotation z value.
     */
    private void differentialDrive(double leftSpeed, double rightSpeed, double forward, double rotation) {
        // SmartDashboard.putNumber("CallTime", LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        leftMotor.set(leftSpeed * MathUtil.clamp(forward + rotation, -1, 1));
        rightMotor.set(rightSpeed * MathUtil.clamp(forward - rotation, -1, 1));
    }


    /**
     * Similar to {@link DriveSubsystem#differentialDrive(double, double, double, double)}, but it allows some parameters to be Dimensionless.
     * 
     * @param leftSpeed The maximum output of the left motor.
     * @param rightSpeed The maximum output of the right motor.
     * @param forward The forward input to use in Dimensionless units.
     * @param rotation The rotation z value in Dimensionless units.
     */
    private void differentialDrive(double leftSpeed, double rightSpeed, Dimensionless forward, Dimensionless rotation) {
        differentialDrive(leftSpeed, rightSpeed, forward.in(Value), rotation.in(Value));
    }

    /**
     * Adjusts the speed of the drive for differential driving.
     * 
     * @param speed The maximum speed of the motors.
     * @param forward The forward input to use.
     * @param rotation The rotation z value.
     */
    public void differentialDrive(double speed, double forward, double rotation) {
        differentialDrive(speed, speed, forward, rotation);
    }

    /**
     * Adjusts the speed of the drive for differential driving using the Units library.
     * 
     * @param speed The maximum speed of the motors.
     * @param forward The forward input to use.
     * @param rotation The rotation z value.
     */
    public void differentialDrive(Dimensionless speed, Dimensionless forward, Dimensionless rotation) {
        differentialDrive(speed.in(Value), speed.in(Value), forward, rotation);
    }

    /**
     * Adjusts the speed of the drive for differential driving using the Units library.
     * 
     * @param speed The maximum speed of the motors in linear velocity.
     * @param forward The forward input to use.
     * @param rotation The rotation z value.
     */
    public void differentialDrive(LinearVelocity speed, Dimensionless forward, Dimensionless rotation) {
        double desiredRps = speed.div(DriveConstants.kWheelDistancePerSecondPerRps).in(Value);
        differentialDrive(desiredRps / getCurrentLeftRps(), desiredRps / getCurrentRightRps(), forward, rotation);
    }

    /**
     * Adjusts the speed of the drive for differential driving using the Units library.
     * 
     * @param speed The maximum speed of the motors in linear velocity.
     * @param forward The forward input to use.
     * @param rotation The rotation z value.
     */
    public void differentialDrive(AngularVelocity speed, Dimensionless forward, Dimensionless rotation) {
        double desiredRps = speed.div(DriveConstants.kWheelAngularVelocityPerRps).in(Value);
        differentialDrive(desiredRps / getCurrentLeftRps(), desiredRps / getCurrentRightRps(), forward, rotation);
    }

    /** 
     * Stops the movement of the drive.
     */
    public void stop() {
        leftMotor.stopMotor();
        rightMotor.stopMotor();
    }

    /**
     * Enables brakes on the drive.
     */
    public void brake() {
        leftMotor.setNeutralMode(NeutralModeValue.Brake);
        rightMotor.setNeutralMode(NeutralModeValue.Brake);
    }
}
