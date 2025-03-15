using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Text.Json.Serialization;
using System.Threading.Tasks;

namespace HabitManager.Models
{
    class CreateUserModel
    {
        [JsonPropertyName("id")]
        public int id;
        [JsonPropertyName("firstname")]
        public string FirstName;
        [JsonPropertyName("lastname")]
        public string LastName;
        [JsonPropertyName("age")]
        public int Age;
        [JsonPropertyName("email")]
        public string Email;
        [JsonPropertyName("password")]
        public string Password;
    }
}