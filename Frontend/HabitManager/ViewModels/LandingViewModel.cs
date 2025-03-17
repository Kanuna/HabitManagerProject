using HabitManager.ApiService;
using ReactiveUI;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;

namespace HabitManager.ViewModels
{
    class LandingViewModel : ReactiveObject
    {
        private bool _isPopupVisible;

        private readonly UserEndpoint _userEndpoint;
        public LandingViewModel(UserEndpoint userEndpoint)
        {
            _userEndpoint = userEndpoint;
            ShowPopupCommand = ReactiveCommand.Create(() => IsPopupVisible = true);
        }

        public ICommand ShowPopupCommand { get; }


        public bool IsPopupVisible
        {
            get => _isPopupVisible;
            set => this.RaiseAndSetIfChanged(ref _isPopupVisible, value);
        }
    }
}