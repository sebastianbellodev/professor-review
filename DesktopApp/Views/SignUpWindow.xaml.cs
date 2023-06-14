using ProfessorPerformanceEvaluation.Model;
using ProfessorPerformanceEvaluation.Service;
using System.Configuration;
using System.Net;
using System.Reflection;
using System.Windows;

namespace ProfessorPerformanceEvaluation.Views
{
    public partial class SignUpWindow : Window
    {
        public SignUpWindow()
        {
            InitializeComponent();
            GenerateToken();
        }

        private async void GenerateToken()
        {
            Response response = await UserService.SignUp();
            if (response.Code == (int)HttpStatusCode.OK)
            {
                Configuration configuration = ConfigurationManager.OpenExeConfiguration(Assembly.GetExecutingAssembly().Location);
                KeyValueConfigurationElement token = configuration.AppSettings.Settings["TOKEN"];
                token.Value = response.Token;
                configuration.Save();
                ConfigurationManager.RefreshSection("appSettings");
            }
            else if (response.Code == (int)HttpStatusCode.Forbidden)
            {
                MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                    Properties.Resources.EXPIRED_SESSION_LABEL);
                Close();
            }
            else
            {
                MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                    Properties.Resources.SERVICE_NOT_AVAILABLE_LABEL);
                Close();
            }
        }

        private void CancelButtonClick(object sender, RoutedEventArgs e)
        {
            Close();
        }

        private void AcceptButtonClick(object sender, RoutedEventArgs e)
        {
            if (!CheckEmptyFields())
            {
                string username = UsernameTextBox.Text;
                string password = PasswordBox.Password.ToString();
                string passwordConfirmation = PasswordConfirmationBox.Password.ToString();
                if (password == passwordConfirmation)
                {
                    password = Utilities.Utilities.ComputeSHA256Hash(password);
                    var user = new User()
                    {
                        Username = username,
                        Password = password
                    };
                    SignUp(user);
                }
                else
                {
                    MessageBox.Show(Properties.Resources.CHECK_ENTERED_INFORMATION_LABEL,
                        Properties.Resources.INVALID_DATA_LABEL);
                }
            }
            else
            {
                MessageBox.Show(Properties.Resources.CHECK_ENTERED_INFORMATION_LABEL,
                    Properties.Resources.EMPTY_FIELDS_LABEL);
            }
        }

        private bool CheckEmptyFields()
        {
            return string.IsNullOrWhiteSpace(UsernameTextBox.Text) &&
                string.IsNullOrWhiteSpace(PasswordBox.Password.ToString()) &&
                string.IsNullOrWhiteSpace(PasswordConfirmationBox.ToString());
        }

        private async void SignUp(User user)
        {
            Response response = await UserService.Post(user);
            switch (response.Code)
            {
                case (int)HttpStatusCode.Created:
                    MessageBox.Show(Properties.Resources.REGISTERED_INFORMATION_LABEL);
                    Close();
                    break;
                case (int)HttpStatusCode.BadRequest:
                    MessageBox.Show(Properties.Resources.CHECK_ENTERED_INFORMATION_LABEL,
                        Properties.Resources.USER_ALREADY_REGISTERED_LABEL);
                    break;
                case (int)HttpStatusCode.Forbidden:
                    MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                        Properties.Resources.EXPIRED_SESSION_LABEL);
                    Close();
                    break;
                default:
                    MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                        Properties.Resources.SERVICE_NOT_AVAILABLE_LABEL);
                    Close();
                    break;
            }
        }
    }
}