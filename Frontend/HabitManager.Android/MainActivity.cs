using Android.App;
using Android.Content;
using Android.Content.PM;
using Plugin.LocalNotification;

using Avalonia;
using Avalonia.Android;
using Avalonia.ReactiveUI;
using AndroidX.Core.App;
using Android.OS;

namespace HabitManager.Android;

[Activity(
    Label = "HabitManager.Android",
    Theme = "@style/MyTheme.NoActionBar",
    Icon = "@drawable/icon",
    MainLauncher = true,
    ConfigurationChanges = ConfigChanges.Orientation | ConfigChanges.ScreenSize | ConfigChanges.UiMode)]
public class MainActivity : AvaloniaMainActivity<App>
{
    protected override AppBuilder CustomizeAppBuilder(AppBuilder builder)
    {
        return base.CustomizeAppBuilder(builder)
            .WithInterFont()
            .UseReactiveUI();
    }

    protected override void OnCreate(Bundle savedInstanceState)
    {
        base.OnCreate(savedInstanceState);
        CreateNotificationChannel();


        SendNotification();
    }

    private void CreateNotificationChannel()
    {
        if (Build.VERSION.SdkInt >= BuildVersionCodes.O)
        {
            var channel = new NotificationChannel("habit_channel", "Habit Notifications",
                NotificationImportance.Default)
            {
                Description = "Sends habit reminders"
            };

            var manager = (NotificationManager)GetSystemService(NotificationService);
            manager.CreateNotificationChannel(channel);
        }
    }

    // Method to send the notification
    public void SendNotification()
    {
        var intent = new Intent(this, typeof(MainActivity));
        var pendingIntent = PendingIntent.GetActivity(this, 0, intent, PendingIntentFlags.Immutable);

        var builder = new NotificationCompat.Builder(this, "habit_channel")
            .SetContentTitle("Habit Reminder")
            .SetContentText("Don't forget to track your habits!")
            .SetSmallIcon(Resource.Drawable.Icon) // Ensure you have an icon resource
            .SetContentIntent(pendingIntent)
            .SetAutoCancel(true);

        var manager = NotificationManagerCompat.From(this);
        manager.Notify(1000, builder.Build());
    }
}