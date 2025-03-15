using Avalonia.Controls.Primitives;
using HabitManager.ApiService;
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


        public CreateUserViewModel(UserEndpoint userEndpoint)
        {
            _userEndpoint = userEndpoint;
        }


        public string ErrorMessage
        {
            get => _errorMessage;
            set
            {
                if(_errorMessage != value)
                {
                    _errorMessage = value;
                    OnPropertyChanged(nameof(ErrorMessage));
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
                    _firstname = value;
                    OnPropertyChanged(nameof(LastName));
                }
            }
        }

        public int Age
        {
            get => _age;
            set
            {
                if (_age > 0 && _age != value)
                {
                    _age = value;
                    OnPropertyChanged(nameof(Age));
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
                    OnPropertyChanged(nameof(IsPasswordValid));
                    ValidatePassword();
                }
            }
        }

        public bool IsPasswordValid => !string.IsNullOrWhiteSpace(Password) && Password.Length > 8 && Regex.IsMatch(Password, @"\d");

        public bool CanCreate =>
            !string.IsNullOrWhiteSpace(FirstName)
            && !string.IsNullOrWhiteSpace(LastName)
            && Age > 0
            && !string.IsNullOrWhiteSpace(Email) && Regex.IsMatch(Email, @"^[^@\s]+@[^@\s]+\.[^@\s]+$")
            && IsPasswordValid;


        public void ValidatePassword()
        {
            if (!IsPasswordValid && Password.Length < 8)
            {
                ErrorMessage = "Password must be minum 8 characters"; //and contain at least one digit
            }
            else
            {
                ErrorMessage = string.Empty;
            }
        }


        public event PropertyChangedEventHandler? PropertyChanged;
        private void OnPropertyChanged(string propertyName) =>
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
    }
}
