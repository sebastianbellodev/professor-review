using ProfessorPerformanceEvaluation.Model;
using ProfessorPerformanceEvaluation.Service;
using ProfessorPerformanceEvaluation.Views;
using System.Configuration;
using System.Net;
using System.Reflection;
using System.Windows; 

namespace ProfessorPerformanceEvaluation
{
    public partial class LoginWindow : Window
    {
        public LoginWindow()
        {
            InitializeComponent();
        }

        private void LoginButtonClick(object sender, RoutedEventArgs e)
        {
            string username = UsernameTextBox.Text;
            string password = PasswordBox.Password.ToString();
            if (!string.IsNullOrWhiteSpace(username) && !string.IsNullOrWhiteSpace(password))
            {
                password = Utilities.Utilities.ComputeSHA256Hash(password);
                var user = new User()
                {
                    Username = username,
                    Password = password
                };
                Login(user);
            } else
            {
                MessageBox.Show(Properties.Resources.CHECK_ENTERED_INFORMATION_LABEL,
                    Properties.Resources.EMPTY_FIELDS_LABEL);
            }
        }

        private async void Login(User user)
        {
            Response response = await UserService.Login(user);
            switch (response.Code)
            {
                case (int)HttpStatusCode.OK:
                    Configuration configuration = ConfigurationManager.OpenExeConfiguration(Assembly.GetExecutingAssembly().Location);
                    KeyValueConfigurationElement token = configuration.AppSettings.Settings["TOKEN"];
                    token.Value = response.Token;
                    configuration.Save();
                    ConfigurationManager.RefreshSection("appSettings");
                    GoToMainMenu();
                    break;
                case (int)HttpStatusCode.NotFound:
                    MessageBox.Show(Properties.Resources.CHECK_ENTERED_INFORMATION_LABEL,
                        Properties.Resources.INVALID_DATA_LABEL);
                    break;
                case (int)HttpStatusCode.Forbidden:
                    MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                        Properties.Resources.EXPIRED_SESSION_LABEL);
                    break;
                default:
                    MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                        Properties.Resources.SERVICE_NOT_AVAILABLE_LABEL);
                    break;
            }
        }

        private void GoToMainMenu()
        {
            var mainMenuWindow = new MainMenuWindow();
            Close();
            mainMenuWindow.Show();
        }

        private void SignUpButtonClick(object sender, RoutedEventArgs e)
        {
            var signUpWindow = new SignUpWindow();
            Close();
            signUpWindow.Show();
        }
    }
}