using Avalonia.Controls.Primitives;
using HabitManager.ApiService;
using HabitManager.Models;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace HabitManager.ViewModels
{
    class CreateUserViewModel : INotifyPropertyChanged
    {
        private readonly UserEndpoint _userEndpoint;
        private string _firstname;
        private string _lastname;
        private int _age;
        private string _email;
        private string _password;
        private string _errorMessage;
        private bool _showError;

        public CreateUserViewModel(UserEndpoint userEndpoint)
        {
            _userEndpoint = userEndpoint;
        }

        public string ErrorMessage
        {
            get => _errorMessage;
            set
            {
                if (_errorMessage != value)
                {
                    _errorMessage = value;
                    OnPropertyChanged(nameof(ErrorMessage));
                }
            }
        }

        public bool ShowError
        {
            get => _showError;
            set
            {
                if (_showError != value)
                {
                    _showError = value;
                    OnPropertyChanged(nameof(ShowError));
                }
            }
        }

        public string FirstName
        {
            get => _firstname;
            set
            {
                if (_firstname != value)
                {
                    _firstname = value;
                    OnPropertyChanged(nameof(FirstName));
                    OnPropertyChanged(nameof(CanCreate));
                }
            }
        }

        public string LastName
        {
            get => _lastname;
            set
            {
                if (_lastname != value)
                {
                    _lastname = value;
                    OnPropertyChanged(nameof(LastName));
                    OnPropertyChanged(nameof(CanCreate));
                }
            }
        }

        public int Age
        {
            get => _age;
            set
            {
                if ( _age != value)
                {
                    _age = value;
                    OnPropertyChanged(nameof(Age));
                    OnPropertyChanged(nameof(CanCreate));
                }
            }
        }

        public string Email
        {
            get => _email;
            set
            {
                if (_email != value)
                {
                    _email = value;
                    OnPropertyChanged(nameof(Email));
                    OnPropertyChanged(nameof(CanCreate));
                }
            }
        }

        public string Password
        {
            get => _password;
            set
            {
                if (_password != value)
                {
                    _password = value;
                    OnPropertyChanged(nameof(Password));
                    OnPropertyChanged(nameof(CanCreate));
                    ValidatePassword();
                }
            }
        }

        public bool CanCreate =>
            !string.IsNullOrWhiteSpace(FirstName)
            && !string.IsNullOrWhiteSpace(LastName)
            && Age > 0
            && !string.IsNullOrWhiteSpace(Email) && Regex.IsMatch(Email, @"^[^@\s]+@[^@\s]+\.[^@\s]+$")
            && !string.IsNullOrWhiteSpace(Password) && Password.Length > 8 && Regex.IsMatch(Password, @"\d");


        public bool ShowErrorMessage => ShowError == true;

        public void ValidatePassword()
        {
            if (string.IsNullOrWhiteSpace(Password))
            {
                ErrorMessage = "Password cannot be empty.";
                ShowError = true;
            }
            else if (Password.Length < 8 && !Regex.IsMatch(Password, @"\d"))
            {
                ErrorMessage = "Password must be at least 8 characters long and contain at least one digit.";
                ShowError = true;
            }
            else if (Password.Length >= 8 && !Regex.IsMatch(Password, @"\d"))
            {
                ErrorMessage = "Password must contain at least one digit.";
                ShowError = true;
            }
            else if (Password.Length < 8 && Regex.IsMatch(Password, @"\d"))
            {
                ErrorMessage = "Password must be minimum 8 characters.";
                ShowError = true;
            }
            else
            {
                ErrorMessage = string.Empty;
                ShowError = false;
            }
        }

        public event PropertyChangedEventHandler? PropertyChanged;
        private void OnPropertyChanged(string propertyName) =>
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));

        public async Task<CreateUserModel> CreateUser()
        {
            try
            {
                CreateUserModel user = await _userEndpoint.CreateUserAsync(FirstName, LastName, Age, Email, Password);
                return user;
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex);
                return null;
            }
        }
    }
}
