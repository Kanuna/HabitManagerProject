using Avalonia;
using Avalonia.Controls;
using Avalonia.Interactivity;
using Avalonia.Markup.Xaml;
using HabitManager.ViewModels;

namespace HabitManager;

public partial class CreateUserView : UserControl
{
    public CreateUserView()
    {
        InitializeComponent();
        this.DataContext = new CreateUserViewModel();
    }

    public void CreateButton_Click(object sender, RoutedEventArgs args)
    {

    }

    public void BackButton_Click(object sender, RoutedEventArgs args)
    {

    }
}