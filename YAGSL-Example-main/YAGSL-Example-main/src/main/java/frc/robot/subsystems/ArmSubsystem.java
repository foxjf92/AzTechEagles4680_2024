package frc.robot.subsystems;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkBase.SoftLimitDirection;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.commands.MoveArmCommand;

public class ArmSubsystem extends SubsystemBase {
    
    private CANSparkMax armMotor; //Move to a certain position
    public double armspeed; 
    public RelativeEncoder armEncoder;

    public double setpoint;
    public double errorSum;
    public double lastTimestamp;
    public double lastError;
    //public double test = 6.5;

    // private float kUpperLimitArmMotor = 65;   //TODO confirm which was is up or down and set
    // private float kLowerLimitArmMotor = -5;  //TODO confirm which way is up or down and set

    public ArmSubsystem(){
        armMotor = new CANSparkMax (9, CANSparkLowLevel.MotorType.kBrushless);
   
        armMotor.setSmartCurrentLimit(40);
        armMotor.setIdleMode(IdleMode.kCoast);
        // armMotor.enableSoftLimit(SoftLimitDirection.kForward, true);
        // armMotor.enableSoftLimit(SoftLimitDirection.kReverse, true);
        //armMotor.setSoftLimit(SoftLimitDirection.kForward, kUpperLimitArmMotor); 
        //armMotor.setSoftLimit(SoftLimitDirection.kReverse, kLowerLimitArmMotor);    
      
        armEncoder = armMotor.getEncoder();
    }

    @Override
    public void periodic(){
        //SmartDashboard.putString("Test", "chickendog");
        SmartDashboard.putNumber("arm encoder: ", armEncoder.getPosition());
        SmartDashboard.putNumber("Arm setpoint", MoveArmCommand.armSetpoint);
    }

    public void moveArm(double armspeed) {
        armMotor.set(armspeed);
    }

}

