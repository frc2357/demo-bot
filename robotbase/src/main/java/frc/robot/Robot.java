// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.units.Units.Value;

import java.util.function.Supplier;

import edu.wpi.first.units.measure.Dimensionless;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Constants.ControllerConstants;
import frc.robot.commands.drive.DefaultDrive;
import frc.robot.controls.DriverControls;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

/**
 * The methods in this class are called automatically corresponding to each mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the Main.java file in the project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "Test";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  public static Robot currentInstance;
  public static DriverControls driverControls;
  public static DriveSubsystem driveSubsystem;
  public static IntakeSubsystem intakeSubsystem;
  private final DefaultDrive m_defaultDrive;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  public Robot() {
    driveSubsystem = new DriveSubsystem();
    intakeSubsystem = new IntakeSubsystem();
    driverControls = new DriverControls();

    Supplier<Dimensionless> speedSupplier = () -> Value.of(SmartDashboard.getNumber("Driving Speed", ControllerConstants.kDefaultDriverSpeed.in(Value)));
    SmartDashboard.putNumber("Driving Speed", speedSupplier.get().in(Value));

    m_defaultDrive = new DefaultDrive(
      speedSupplier, 
      driverControls::getForwardInput, 
      driverControls::getRotationZInput
    );
    // driveSubsystem.setDefaultCommand(new DefaultDrive());

    m_chooser.setDefaultOption("Default", kDefaultAuto);
    m_chooser.addOption("Drive Test", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    // SmartDashboard.putBoolean("TeleOp", false);

    currentInstance = this;
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();

    // Command command = driveSubsystem.getCurrentCommand();
    // String name;
    // if (command == null) {
    //   name = "null";
    // } else {
    //   name = command.getName();
    // }
    // SmartDashboard.putString("Current Command", name);
    // SmartDashboard.putString("Hello", "World");
    // SmartDashboard.putData(driveSubsystem);
    // SmartDashboard.updateValues();
    // SmartDashboard.putBoolean("In Default Drive", CommandScheduler.getInstance().isScheduled(m_defaultDrive));
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);

    // if (m_autoSelected == kCustomAuto) {
    //   // CommandScheduler.getInstance().schedule(new DefaultDrive());
    // }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    CommandScheduler.getInstance().schedule(m_defaultDrive);
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    SmartDashboard.putNumber("Drive Forward", driverControls.getForwardInput().in(Value));
    SmartDashboard.putNumber("Drive RotationZ", driverControls.getRotationZInput().in(Value));
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
