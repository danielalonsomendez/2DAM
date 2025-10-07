namespace _09Average
{
    internal class Program
    {
        public class SnowFallData
        {
            public int Year { get; set; }
            public List<MonthlySnowFallData>? MonthlySnowFallDataItems { get; set; }
        }

        public class MonthlySnowFallData
        {
            public int Month { get; set; }
            public float SnowfallInCentimeters { get; set; }
        }

        public class Student
        {
            public IEnumerable<int>? Marks { get; set; }
        }

        static void Main(string[] args)
        {
            // Ejemplo 1: Promedio de una lista de números
            var numeros = new List<int> { 1, 2, 3 };
            Console.WriteLine(numeros.Average());

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
            var averageWeightofpets = pets.Average(p => p.Weight);
            Console.WriteLine(averageWeightofpets);
            // Ejercicio 1: Promedio de nieve caída en un año
            var snowFallData = new SnowFallData()
            {
                Year = 2024,
                MonthlySnowFallDataItems = new List<MonthlySnowFallData>
                {
                    new MonthlySnowFallData {Month=1,SnowfallInCentimeters = 11},
                    new MonthlySnowFallData {Month=2,SnowfallInCentimeters = 22},
                    new MonthlySnowFallData {Month=3,SnowfallInCentimeters = 10},
                    new MonthlySnowFallData {Month=4,SnowfallInCentimeters = 9},
                    new MonthlySnowFallData {Month=5,SnowfallInCentimeters = 5},
                    new MonthlySnowFallData {Month=6,SnowfallInCentimeters = 0},
                    new MonthlySnowFallData {Month=7,SnowfallInCentimeters = 0},
                    new MonthlySnowFallData {Month=8,SnowfallInCentimeters = 1},
                    new MonthlySnowFallData {Month=9, SnowfallInCentimeters = 5},
                    new MonthlySnowFallData {Month=10, SnowfallInCentimeters = 18},
                    new MonthlySnowFallData {Month=11, SnowfallInCentimeters = 19},
                    new MonthlySnowFallData {Month=12, SnowfallInCentimeters = 32},
                }
            };

            Console.WriteLine(AverageSnowFall(snowFallData));  // 11

            // Ejercicio 2: Promedio máximo de calificaciones entre estudiantes
            var students = new List<Student>
            {
                new Student {Marks = new List<int> {4,4,6}},
                new Student {Marks = new List<int> {5,5,5,3}},
                new Student {Marks = new List<int> {6,5,5,3}},
                new Student {Marks = new List<int>()}
            };
            Console.WriteLine(MaxAverageOfMarks(students));  //4.75


        }
        static float AverageSnowFall(SnowFallData data)
        {
            if (data.MonthlySnowFallDataItems == null || data.MonthlySnowFallDataItems.Count == 0)
            {
                return 0;
            }
            return data.MonthlySnowFallDataItems.Average(m => m.SnowfallInCentimeters);
        }

        static double MaxAverageOfMarks(List<Student> students)
        {
            return students.Any()? students.Max(s => s.Marks != null && s.Marks.Any() ? s.Marks.Average() : 0):0;
        }
    }
}
