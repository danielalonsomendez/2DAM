namespace _07OrderBy
{
    internal class Program
    {
        static void Main(string[] args)
        {
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
            var petsOrderedByName = pets.OrderBy(p => p.Name);
            foreach (var pet in petsOrderedByName)
            {
                Console.WriteLine(pet.Id + "  " +pet.Name);
            } 
            var petsOrderedByTypeThenName = pets.OrderBy(p => p.PetType).ThenBy(p => p.Name);
            foreach (var pet in petsOrderedByTypeThenName)
            {
                Console.WriteLine(pet.Id + "  " + pet.Name + "  " + pet.PetType);
            }
            var numbers2 = new[] { 1, 2, 3, 4, 5, 6, 7 };
            var numbersOrderedOdd = numbers2.OrderBy(n => n % 2 != 0).ThenByDescending(n =>n);
            Console.WriteLine(string.Join(", ", numbersOrderedOdd));

        }
    }
}
