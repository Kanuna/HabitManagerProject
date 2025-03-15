using Avalonia;
using Avalonia.Controls;
using Avalonia.Interactivity;
using Avalonia.Markup.Xaml;
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

    public void CreateButton_Click(object sender, RoutedEventArgs args)
    {
        createUserViewModel.ErrorMessage = "Hehe";
    }

    public void BackButton_Click(object sender, RoutedEventArgs args)
    {

    }
}