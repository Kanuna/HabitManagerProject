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
    class LoginUserViewModel : INotifyPropertyChanged
    {
        private readonly UserEndpoint _userEndpoint;
        private string _email;
        private string _password;
        private string _errorMessage;
        private bool _showError;


        public LoginUserViewModel(UserEndpoint userEndpoint)
        {
            _userEndpoint = userEndpoint;
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
                    OnPropertyChanged(nameof(CanLogin));
                    ValidateEmail();
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
                }
            }
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

        public bool ShowErrorMessage => ShowError == true;
        public bool CanLogin => !string.IsNullOrWhiteSpace(Email) && Regex.IsMatch(Email, @"^[^@\s]+@[^@\s]+\.[^@\s]+$");


        public void ValidateEmail()
        {
            if(!Regex.IsMatch(Email, @"^[^@\s]+@[^@\s]") && !Regex.IsMatch(Email, @"\.[^@\s]+$"))
            {
                ErrorMessage = "Email must contain '@' and '.'.";
                ShowError = true;
            }
            else if(!Regex.IsMatch(Email, @"^[^@\s]+@[^@\s]")){
                ErrorMessage = "Email must contain '@'.";
                ShowError = true;
            }
            else if(!Regex.IsMatch(Email, @"\.[^@\s]+$"))
            {
                ErrorMessage = "Email must contain '.'.";
                ShowError = true;
            }
            else
            {
                ErrorMessage = string.Empty;
                ShowError = false;
            }
        }


        public async Task<UserModel> LoginUser()
        {
            try
            {
                UserModel user = await _userEndpoint.LoginUser(Email, Password);

                if (user == null)
                {
                    ErrorMessage = "Invalid email or password";
                }

                return user;
            }
            catch (Exception ex)
            {
                ErrorMessage = "An error occurred while logging in.";
                return null;
            }
        }


        public event PropertyChangedEventHandler? PropertyChanged;

        private void OnPropertyChanged(string propertyName) =>
           PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));

    }
}
