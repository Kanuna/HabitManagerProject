using Avalonia;
using Avalonia.Controls;
using Avalonia.Interactivity;
using Avalonia.Markup.Xaml;
using HabitManager.ViewModels;

namespace HabitManager;

public partial class StartView : UserControl
{
    public StartView()
    {
        InitializeComponent();
    }

    public void CreateButton_Click(object sender, RoutedEventArgs args)
    {
        this.Content = new CreateUserView();
        this.DataContext = new CreateUserViewModel();
    }    
    
    public void LoginButton_Click(object sender, RoutedEventArgs args)
    {
        this.Content = new LoginUserView();
        this.DataContext = new LoginUserViewModel();


    }
}