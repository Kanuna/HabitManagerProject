using Avalonia;
using Avalonia.Controls;
using Avalonia.Interactivity;
using Avalonia.Markup.Xaml;
using HabitManager.Models;
using HabitManager.ViewModels;
using System.Threading.Tasks;

namespace HabitManager;

public partial class LoginUserView : UserControl
{
    private readonly LoginUserViewModel loginUserViewModel = new(new ApiService.UserEndpoint());

    public LoginUserView()
    {
        InitializeComponent();
        this.DataContext = loginUserViewModel;
    }

    public void CreateButton_Click(object sender, RoutedEventArgs args)
    {
        this.Content = new CreateUserView();
    }

    public async void LoginButton_Click(object sender, RoutedEventArgs args)
    {
        UserModel user = await loginUserViewModel.LoginUser();

        if (user != null)
        {
            this.Content = new LandingView();
        }
        
    }
}