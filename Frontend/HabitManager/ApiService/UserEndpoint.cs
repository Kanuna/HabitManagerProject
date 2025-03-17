using HabitManager.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;

namespace HabitManager.ApiService
{
    class UserEndpoint
    {
        public async Task<UserModel> CreateUser(string firstname, string lastname, int age, string email, string password)
        {
            var payload = new { firstname, lastname, age, email, password };
            string json = JsonSerializer.Serialize(payload);
            StringContent content = new(json, Encoding.UTF8, "application/json");

            HttpResponseMessage response = await ApiClient.Instance.PostAsync("/user/user", content);

            if (response.IsSuccessStatusCode)
            {
                string responseJson = await response.Content.ReadAsStringAsync();
                return JsonSerializer.Deserialize<UserModel>(responseJson, new JsonSerializerOptions { PropertyNameCaseInsensitive = true });
            }

            return null;
        }

        public async Task<UserModel> LoginUser(string email, string password)
        {
            var payload = new { email, password };
            string json = JsonSerializer.Serialize(payload);
            StringContent content = new(json, Encoding.UTF8, "application/json");

            HttpResponseMessage response = await ApiClient.Instance.PostAsync("/user/login", content); // Fix URL

            if (response.IsSuccessStatusCode)
            {
                string responseJson = await response.Content.ReadAsStringAsync();
                return JsonSerializer.Deserialize<UserModel>(responseJson, new JsonSerializerOptions { PropertyNameCaseInsensitive = true });
            }

            return null;
        }
    }
}