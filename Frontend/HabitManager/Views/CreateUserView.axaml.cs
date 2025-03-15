using Avalonia;
using Avalonia.Controls;
using Avalonia.Interactivity;
using Avalonia.Markup.Xaml;
using HabitManager.Models;
using HabitManager.ViewModels;

namespace HabitManager;

public partial class CreateUserView : UserControl
{
    private readonly CreateUserViewModel createUserViewModel = new CreateUserViewModel(new ApiService.UserEndpoint());

    public CreateUserView()
    {
        InitializeComponent();
        DataContext = createUserViewModel;
    }

    public async void CreateButton_Click(object sender, RoutedEventArgs args)
    {
        CreateUserModel user = await createUserViewModel.CreateUser();
        if (user != null) {
            this.Content = new LandingView();
        }
    }

    public void BackButton_Click(object sender, RoutedEventArgs args)
    {

    }
}