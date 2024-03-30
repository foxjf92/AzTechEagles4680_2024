package frc.robot.subsystems;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.Teleop.MoveArmCommand;

public class ArmSubsystem extends SubsystemBase {
    
    public static CANSparkMax armMotor;
    public DutyCycleEncoder armEncoder;
    public double armPosition;
    
    public ArmSubsystem(){
        armMotor = new CANSparkMax (9, CANSparkLowLevel.MotorType.kBrushless);
        armMotor.setSmartCurrentLimit(40);
        armMotor.setIdleMode(IdleMode.kBrake);
        // armMotor.setIdleMode(IdleMode.kCoast);
        
        armEncoder = new DutyCycleEncoder(9);
        
        //armEncoder = armMotor.getEncoder();

        //// I don't think we need these with a properly tensioned chain and proper PID
        // armMotor.enableSoftLimit(SoftLimitDirection.kForward, true);
        // armMotor.enableSoftLimit(SoftLimitDirection.kReverse, true);
        //armMotor.setSoftLimit(SoftLimitDirection.kForward, kUpperLimitArmMotor); 
        //armMotor.setSoftLimit(SoftLimitDirection.kReverse, kLowerLimitArmMotor);    
    
    }

    @Override
    public void periodic(){

        armPosition = armEncoder.getAbsolutePosition();
        SmartDashboard.putNumber("Arm Position",armPosition);
        SmartDashboard.putNumber("Arm Setpoint", MoveArmCommand.armSetpoint);
    }

    public void moveArm(double armSpeed){
        armMotor.set(armSpeed);
    }

    public void setArmBrake(){
        armMotor.setIdleMode(IdleMode.kBrake);
    }

}

