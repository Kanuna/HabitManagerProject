using Avalonia;
using Avalonia.Controls;
using Avalonia.Interactivity;
using Avalonia.Markup.Xaml;
using HabitManager.Models;
using HabitManager.ViewModels;

namespace HabitManager;

public partial class CreateUserView : UserControl
{
    private readonly CreateUserViewModel createUserViewModel = new(new ApiService.UserEndpoint());

    public CreateUserView()
    {
        InitializeComponent();
        DataContext = createUserViewModel;
    }

    public async void CreateButton_Click(object sender, RoutedEventArgs args)
    {
        UserModel user = await createUserViewModel.CreateUser();
        if (user != null)
        {
            this.Content = new LoginUserView();
        }
    }

    public void LoginButton_Click(object sender, RoutedEventArgs args)
    {
        this.Content = new LoginUserView();
    }
}