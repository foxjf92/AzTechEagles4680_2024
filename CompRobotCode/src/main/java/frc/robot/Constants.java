// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.util.PIDConstants;

import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.util.Units;
import swervelib.math.Matter;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean constants. This
 * class should not be used for any other purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants
{

  public static final double ROBOT_MASS = 80 * 0.453592; // 70lbs * kg per pound
  public static final Matter CHASSIS    = new Matter(new Translation3d(0, 0, Units.inchesToMeters(14)), ROBOT_MASS);
  public static final double LOOP_TIME  = 0.13; //s, 20ms + 110ms sprk max velocity lag


  public static final class AutonConstants
  {

    public static final PIDConstants TRANSLATION_PID = new PIDConstants(0.7, 0, 0);
    public static final PIDConstants ANGLE_PID   = new PIDConstants(0.4, 0, 0.01);
  }

  public static final class DrivebaseConstants
  {

    // Hold time on motor brakes when disabled
    public static final double WHEEL_LOCK_TIME = 10; // seconds
  }

  public static class OperatorConstants
  {

    // Joystick Deadband
    public static final double LEFT_X_DEADBAND  = 0.08;
    public static final double LEFT_Y_DEADBAND  = 0.08;
    public static final double RIGHT_X_DEADBAND = 0.08;
    public static final double RIGHT_Y_DEADBAND = 0.08;
    public static final double TURN_CONSTANT    = 6;
  }

  public static class ArmConstants
  {
    //Through bore encoder values
    //public static final double launchPosition = 0.304;  // TODO confirm this is best spot for launching
    public static final double launchPosition = 0.300;  // TODO confirm this is best spot for launching
    public static final double ampPosition = 0.329;     // adjusted to angle down a little
    public static final double intakePosition = 0.52; 

    //Push-feed constants
    // public static final double stagePosition = null; //TODO position to stage Note before push
    // public static final double pushPosition = null; //TODO position to stage Note to push
  }

}
