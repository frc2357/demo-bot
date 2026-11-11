package frc.robot;

import static edu.wpi.first.units.Units.Inches;
import static edu.wpi.first.units.Units.Percent;
import static edu.wpi.first.units.Units.RotationsPerSecond;
import static edu.wpi.first.units.Units.Second;
import static edu.wpi.first.units.Units.Value;

import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Dimensionless;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.units.measure.LinearVelocity;

public class Constants {
    public static final class DriveConstants {
        public static final int kLeftMotorPort = 11;
        public static final int kRightMotorPort = 12;
        
        public static final Distance kWheelDiameter = Inches.of(6);
        public static final double kGearRatio = (42d * 54d) / (13d * 16d);
        public static final LinearVelocity kWheelDistancePerSecondPerRps = kWheelDiameter.times(2 * Math.PI / kGearRatio).per(Second);
        public static final AngularVelocity kWheelAngularVelocityPerRps = RotationsPerSecond.of(1d / kGearRatio);
    }

    public static final class IntakeConstants {
        public static final int kMainMotorPort = 21;
        public static final Dimensionless kDefaultAxisMaxSpeed = Percent.of(20);
    }

    public static final class ControllerConstants {
        public static final int kDriverControllerPort = 0;
        public static final Dimensionless kDefaultDriverSpeed = Value.of(1);
    }
}
