using Avalonia;
using Avalonia.Controls;
using Avalonia.Markup.Xaml;
using System.Collections.Generic;
using System;
using System.Linq;

namespace HabitManager;

public partial class CustomCalendar : UserControl
{
    private Grid _calendarGrid;
    private DateTime _currentDate;
    private DateTime? _selectedDate;

    public CustomCalendar()
    {
        InitializeComponent();
        _calendarGrid = this.FindControl<Grid>("CalendarGrid");
        _currentDate = DateTime.Now;
        LoadCalendar();
    }

    private void InitializeComponent()
    {
        AvaloniaXamlLoader.Load(this);
    }

    private void LoadCalendar()
    {
        // Clear the existing content
        _calendarGrid.Children.Clear();

        // Calculate the start of the week
        DateTime startOfWeek = _currentDate.AddDays(-(int)_currentDate.DayOfWeek);

        // Get the days of the week and the corresponding dates
        var days = Enum.GetValues(typeof(DayOfWeek)).Cast<DayOfWeek>().ToList();
        var dates = new List<int>();

        for (int i = 0; i < 7; i++)
        {
            dates.Add(startOfWeek.AddDays(i).Day);
        }

        // Update month and year display
        var monthYearTextBlock = this.FindControl<TextBlock>("MonthYearText");
        monthYearTextBlock.Text = $"{startOfWeek.ToString("MMMM yyyy")}";

        // Display the days of the week (Mon-Sun) in the first row
        for (int i = 0; i < 7; i++)
        {
            var dayLabel = new TextBlock
            {
                Text = days[i].ToString().Substring(0, 3),
                HorizontalAlignment = Avalonia.Layout.HorizontalAlignment.Center
            };
            _calendarGrid.Children.Add(dayLabel);
            Grid.SetRow(dayLabel, 0); // Place in the first row
            Grid.SetColumn(dayLabel, i); // Place in the correct column
        }

        // Display the dates in the second row, making them clickable
        for (int i = 0; i < 7; i++)
        {
            var dateButton = new Button
            {
                Content = dates[i].ToString(),
                HorizontalAlignment = Avalonia.Layout.HorizontalAlignment.Center,
                VerticalAlignment = Avalonia.Layout.VerticalAlignment.Center,
                Background = _selectedDate == startOfWeek.AddDays(i).Date ? Avalonia.Media.Brushes.LightBlue : Avalonia.Media.Brushes.Transparent
            };

            // Event handler for button click
            dateButton.Click += (sender, e) => OnDateSelected(startOfWeek.AddDays(i));

            _calendarGrid.Children.Add(dateButton);
            Grid.SetRow(dateButton, 1); // Place in the second row
            Grid.SetColumn(dateButton, i); // Place in the correct column
        }
    }

    private void OnDateSelected(DateTime date)
    {
        _selectedDate = date;
        LoadCalendar();
    }

    private void PreviousWeek_Click(object sender, Avalonia.Interactivity.RoutedEventArgs e)
    {
        _currentDate = _currentDate.AddDays(-7);
        LoadCalendar();
    }

    private void NextWeek_Click(object sender, Avalonia.Interactivity.RoutedEventArgs e)
    {
        _currentDate = _currentDate.AddDays(7);
        LoadCalendar();
    }
}