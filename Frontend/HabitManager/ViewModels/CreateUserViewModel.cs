using Avalonia.Controls.Primitives;
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
        private string _firstname;
        private string _lastname;
        private int _age;
        private string _email;
        private string _password;

        private string _errorMessage;


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
                    OnPropertyChanged(nameof(CanCreate));
                }
            }
        }

        public bool IsPasswordValid => !string.IsNullOrEmpty(Password) && Password.Length > 8;

        public bool IsEmailValid => !string.IsNullOrEmpty(Email) && Regex.IsMatch(Email, @"^[^@\s]+@[^@\s]+\.[^@\s]+$");

        public bool CanCreate => IsPasswordValid && IsEmailValid;



        public event PropertyChangedEventHandler? PropertyChanged;
        private void OnPropertyChanged(string propertyName) =>
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
   

    }
}
