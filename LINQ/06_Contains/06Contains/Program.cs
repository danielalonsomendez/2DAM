namespace _06Contains
{
    internal class Program
    {
        static void Main(string[] args)
        {
            var numbers = new[] { 16, 8, 9, -1, 2 };
            bool is7Present = numbers.Contains(7);
            Console.WriteLine(is7Present);

            // con objetos
            var pets = new[]
           {
                new Pet(1, "Hannibal", PetType.Fish, 1.1f),
                new Pet(2, "Anthony", PetType.Cat, 2f),
                new Pet(3, "Ed", PetType.Cat, 0.7f),
                new Pet(4, "Taiga", PetType.Dog, 35f),
                new Pet(5, "Rex", PetType.Dog, 40f),
                new Pet(6, "Lucky", PetType.Dog, 5f),
                new Pet(7, "Storm", PetType.Cat, 0.9f),
                new Pet(8, "Nyan", PetType.Cat, 2.2f)
            };
            bool isPresentHannibal = pets.Contains(new Pet(1, "Hannibal", PetType.Fish, 1.1f));
            Console.WriteLine(isPresentHannibal); // esto da false porque son referencias distintas
            bool isPresentHannibal2 = pets.Contains(pets[0]);
            Console.WriteLine(isPresentHannibal2);


            //
            var words = new[] { "hello", "octopus" };
            var bannedWords = new[] { "lollipop", "octopus", "badger" };

            var wordsAsString = string.Join(", ", words);
            var bannedWordsAsString = string.Join(", ", bannedWords);

            Console.WriteLine(wordsAsString);
            Console.WriteLine(bannedWordsAsString);
            // without LINQ
            bool hasBannedWords = ContainsBannedWords(words, bannedWords);
            Console.WriteLine(hasBannedWords);
            // with LINQ
            bool hasBannedWordsLinq = words.Any(word => bannedWords.Contains(word));
        }
        public static bool ContainsBannedWords(IEnumerable<string> words, IEnumerable<string> bannedWords)
        {
            foreach (var word in words)
            {
                foreach (var bannedWord in bannedWords)
                {
                    if (word == bannedWord)
                    {
                        return true;
                    }
                }
            }
            return false;
        }
    }

}

}
