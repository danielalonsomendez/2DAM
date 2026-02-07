namespace ElorMAUI
{
    public partial class App : Application
    {
        public App()
        {
            InitializeComponent();
            SecureStorage.Remove("elor_logged");
            SecureStorage.Remove("elor_user");
        }

        protected override Window CreateWindow(IActivationState? activationState)
        {
            var window = new Window(new MainPage())
            {
                Title = "ElorMAUI",
                Width = 1100,
                Height = 900,
                MinimumWidth = 500,
                MinimumHeight = 700
            };

            return window;
        }
    }
}
