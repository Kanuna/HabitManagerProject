using Avalonia;
using Avalonia.Controls;
using Avalonia.Markup.Xaml;

namespace HabitManager;

public partial class MenuBar : UserControl
{
    public MenuBar()
    {
        InitializeComponent();
    }

    private void InitializeComponent()
    {
        AvaloniaXamlLoader.Load(this);
    }
}