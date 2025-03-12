using Avalonia.Controls;

namespace HabitManager.Views;

public partial class MainView : UserControl
{
    public MainView()
    {
        InitializeComponent();
        Content = new StartView();
    }
}
