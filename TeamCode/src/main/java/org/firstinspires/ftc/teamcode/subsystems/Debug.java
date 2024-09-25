package org.firstinspires.ftc.teamcode.subsystems;
import com.acmerobotics.dashboard.FtcDashboard;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Debug
{
    static FtcDashboard dashboard;
    static Telemetry dashboardTelemetry;
    static Telemetry dhTelemetry;

    public static void init(Telemetry telemetry, FtcDashboard ftcDashboard)
    {
        dhTelemetry = telemetry;
        dashboard = ftcDashboard;
        dashboardTelemetry = dashboard.getTelemetry();
    }

    public static<T> void log(String caption, T message)
    {
        dhTelemetry.addData(caption, message);
        dashboardTelemetry.addData(caption, message);
        dhTelemetry.update();
        dashboardTelemetry.update();
    }
}
