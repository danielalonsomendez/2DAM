namespace IsAnyWordUppercase
{
    internal class Program
    {
        static void Main(string[] args)
        {
            var wordsNoUppercase = new string[] {
                "quick","brown","fox"
            };
            Console.WriteLine(IsAnyWordUpperCase(wordsNoUppercase));
            var wordsWithUppercase = new string[] {
                "quick","brown","FOX"
            };
            Console.WriteLine(IsAnyWordUpperCase(wordsWithUppercase));
            Console.ReadKey();
        }
        static bool IsAnyWordUpperCase(string[] words)
        {
            bool hasUppercase = false;
            // Sin Utilizar LINQ
            foreach (var word in words)
            {
                foreach (var character in word)
                {
                    if (char.IsUpper(character))
                    {
                        hasUppercase = true;
                        break;
                    }
                }
                if (hasUppercase) break;
            }
            // Utilizando LINQ
            // hasUppercase = words.Any(word => word.Any(char.IsUpper));
            return hasUppercase;
        }
    }
}
