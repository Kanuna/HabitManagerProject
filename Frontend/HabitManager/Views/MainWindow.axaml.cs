using Avalonia.Controls;

namespace HabitManager.Views;

public partial class MainWindow : Window
{
    public MainWindow()
    {
        InitializeComponent();
        Content = new StartView();
    }
}
