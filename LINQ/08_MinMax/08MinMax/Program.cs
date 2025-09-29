namespace _08MinMax
{
    internal class Program
    {
        static void Main(string[] args)
        {
            var words = new[] { "aaa", "bb", "c", "dddd" };
           Console.WriteLine(bbb(words));


            var numbers2 = new[] { 3, 2, 2, 4, 4, 0 };
            Console.WriteLine(CountOfLargestNumber(numbers2));

            var Pets = new[]
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
             var owners = new List<Person>
            {
             new Person(1, "John", new [] {
                   Pets[0],
                   Pets[1],
                   Pets[2]
               }),
               new Person(2, "Jack", new [] {
                   Pets[2]
               }),
               new Person(3, "Stephanie", new [] {
                   Pets[3],  //Taiga
                   Pets[4]   //Rex 
               })
            };
            Console.WriteLine(CountOfDogsOfTheOwnerWithMostDogs(owners));

        }

        public static int? bbb(IEnumerable<String> words)
        {
            if(!words.Any())
            {
                return null;
            }
            return words.MinBy(w => w.Length)?.Length;
        }
        public static int? CountOfLargestNumber(IEnumerable<int> numbers2)
        {
        return numbers2.Count(n =>  n == numbers2.Max());
        }

        public static int? CountOfDogsOfTheOwnerWithMostDogs(IEnumerable<Person> owners)
        {
           return owners.Max(o => o.Pets.Count(p => p.PetType == PetType.Dog));

        }
    }
}
