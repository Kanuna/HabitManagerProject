using Avalonia;
using Avalonia.Controls;
using Avalonia.Interactivity;
using Avalonia.Markup.Xaml;
using HabitManager.ViewModels;
using HabitManager.Views;
using System;

namespace HabitManager;

public partial class LandingView : UserControl
{
    private readonly LandingViewModel landingViewModel = new(new ApiService.UserEndpoint());

    public LandingView()
    {
        InitializeComponent();
        DataContext = landingViewModel;
    }
}