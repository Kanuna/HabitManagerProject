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
        public async Task<CreateUserModel> CreateUserAsync(string firstname, string lastname, int age, string email, string password)
        {
            var payload = new { firstname, lastname, age, email, password };
            string json = JsonSerializer.Serialize(payload);
            StringContent content = new StringContent(json, Encoding.UTF8, "application/json");

            HttpResponseMessage response = await ApiClient.Instance.PostAsync("/user/user", content);

            if(response.IsSuccessStatusCode){
                string responseJson = await response.Content.ReadAsStringAsync();
                return JsonSerializer.Deserialize<CreateUserModel>(responseJson, new JsonSerializerOptions { PropertyNameCaseInsensitive = true });
            }

            return null;
        }
    }
}
