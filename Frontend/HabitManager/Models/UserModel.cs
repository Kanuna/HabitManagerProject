using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Text.Json.Serialization;
using System.Threading.Tasks;

namespace HabitManager.Models
{
    class UserModel
    {
        [JsonPropertyName("id")]
        public int id { get; set; }
        [JsonPropertyName("firstname")]
        public string FirstName { get; set; }
        [JsonPropertyName("lastname")]
        public string LastName {  get; set; }
        [JsonPropertyName("age")]
        public int Age;
        [JsonPropertyName("email")]
        public string Email {  get; set; }
        [JsonPropertyName("password")]
        public string Password { get; set; }
    }
}