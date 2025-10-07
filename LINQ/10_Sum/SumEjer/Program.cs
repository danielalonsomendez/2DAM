using System;
using System.Drawing;
using System.Runtime.ConstrainedExecution;
using static System.Runtime.InteropServices.JavaScript.JSType;

namespace SumEjer
{
    class Product { public string Name; public double Price; public bool InStock; }
    class User { public string Name; public int Points; public List<string> Achievements; }
    class Player { public string Name; public List<int> Points; }
    class Song { public string Title; public int DurationInSeconds; }
    class Employee { public string Name; public int Age; public double Salary; }
    class Category { public string Name; public List<int> Values; }

    internal class Program
    {
        static void Main(string[] args)
        {
            //Ejercicio 1: Dado un array de enteros, calcula la suma de los números pares.
            int[] numbers = { 1, 2, 3, 4, 5, 6 };
            Console.WriteLine(numbers.Sum());
            //Ejercicio 2: Dada una lista de productos, calcula la suma de precios de los que están en stock.
            var products = new List<Product>
            {
                new Product { Name = "Laptop", Price = 1200, InStock = true },
                new Product { Name = "Mouse", Price = 25, InStock = false },
                new Product { Name = "Keyboard", Price = 45, InStock = true }
            };
            Console.WriteLine(products.Sum(p => p.InStock ? p.Price : 0));
            //Ejercicio 3: Dada una lista de calificaciones, suma solo las que sean mayores a 5.
            List<int> grades = new List<int> { 4, 6, 7, 3, 8 };
            Console.WriteLine(grades.Sum(g => g > 5 ? g : 0));

            //Ejercicio 4: Dada una lista de empleados, suma los salarios de los que tienen más de 30 años.
            var employees = new List<Employee>
            {
                new Employee { Name = "Ana", Age = 28, Salary = 2000 },
                new Employee { Name = "Luis", Age = 35, Salary = 2500 },
                new Employee { Name = "Marta", Age = 40, Salary = 3000 }
            };
            Console.WriteLine(employees.Sum(e => e.Age > 30 ? e.Salary : 0));
            //Ejercicio 5: Dado un array de palabras, suma la longitud de las que contienen la letra 'a'.
            string[] words = { "mesa", "silla", "ventana", "puerta", "luz" };
            Console.WriteLine(words.Sum(w => w.Contains('a') ? w.Length : 0));
            //Ejercicio 6: Dado un array de temperaturas, suma las que estén por encima del promedio.
            double[] temps = { 15.5, 18.2, 20.1, 16.8, 22.0 };
            double avgTemp = temps.Average();
            Console.WriteLine(temps.Sum(t => t > avgTemp ? t : 0));
            //Ejercicio 7: Dada una lista de categorías con valores, suma el valor mínimo de cada categoría.
            var categories = new List<Category>
            {
                new Category { Name = "A", Values = new List<int> { 3, 5, 2 } },
                new Category { Name = "B", Values = new List<int> { 7, 1, 4 } }
            };
            Console.WriteLine(categories.Sum(c => c.Values.Min()));
            //Ejercicio 8: Dado un array de enteros, ordénalos de menor a mayor y suma los primeros 3.
            int[] values = { 9, 2, 5, 1, 7 };
            Console.WriteLine(values.OrderBy(v => v).Take(3).Sum());
            //Ejercicio 9: Dada una lista de canciones, suma la duración de las que duren más de 3 minutos y contengan la palabra "love".
            var songs = new List<Song>
            {
                new Song { Title = "Love Story", DurationInSeconds = 240 },
                new Song { Title = "Hate That I Love You", DurationInSeconds = 200 },
                new Song { Title = "Dance", DurationInSeconds = 180 }
            };
            Console.WriteLine(songs.Sum(s => s.DurationInSeconds > 180 && s.Title.Contains("love", StringComparison.OrdinalIgnoreCase) ? s.DurationInSeconds : 0));
            //Ejercicio 10 : Dada una lista de gastos mensuales, suma los que estén por encima del gasto mínimo registrado.
            List<double> expenses = new List<double> { 120.5, 80.0, 150.0, 90.0 };
            double minExpense = expenses.Min();
            Console.WriteLine(expenses.Sum(e => e > minExpense ? e : 0));
            //Ejercicio 11: Dada una lista de jugadores con sus puntos por partido, suma los puntos totales de quienes hayan marcado al menos una vez.
            var players = new List<Player>
            {
                new Player { Name = "Alex", Points = new List<int> { 0, 0, 0 } },
                new Player { Name = "Sara", Points = new List<int> { 5, 3, 0 } },
                new Player { Name = "Leo", Points = new List<int> { 2, 0, 1 } }
            };
            Console.WriteLine(players.Sum(p => p.Points.Any(pt => pt > 0) ? p.Points.Sum() : 0));
            //Ejercicio 12: Dada una lista de productos, suma los precios de aquellos cuyo nombre contenga la palabra "eco".
            var products2 = new List<Product>
            {
                new Product { Name = "EcoBottle", Price = 15.0 },
                new Product { Name = "PlasticCup", Price = 5.0 },
                new Product { Name = "EcoBag", Price = 12.0 }
            };
            Console.WriteLine(products2.Sum(p => p.Name.Contains("eco", StringComparison.OrdinalIgnoreCase) ? p.Price : 0));
            //Ejercicio 13: Dada una lista de frases, suma la cantidad total de caracteres de aquellas que contienen al menos un signo de exclamación.
            List<string> phrases = new List<string>
            {
                "¡Hola mundo!",
                "Esto es una prueba",
                "¡Qué emoción!",
                "Sin exclamación"
            };
            Console.WriteLine(phrases.Sum(p => p.Contains('!') ? p.Length : 0));
            //Ejercicio 14: Dada una lista de arrays de enteros, suma todos los valores de aquellos arrays que contienen al menos un cero.
            List<int[]> matrices = new List<int[]>
            {
                new int[] { 1, 2, 3 },
                new int[] { 0, 4, 5 },
                new int[] { 6, 0, 7 }
            };
            Console.WriteLine(matrices.Sum(arr => arr.Contains(0) ? arr.Sum() : 0));
            //Ejercicio 15: Dada una lista de usuarios con puntos y logros, suma los puntos de quienes tienen más de 3 logros.
            var users = new List<User>
            {
                new User { Name = "Laura", Points = 100, Achievements = new List<string> { "A", "B", "C", "D" } },
                new User { Name = "Mario", Points = 80, Achievements = new List<string> { "A", "B" } },
                new User { Name = "Sofía", Points = 120, Achievements = new List<string> { "A", "B", "C", "D", "E" } }
            };
            Console.WriteLine(users.Sum(u => u.Achievements.Count > 3 ? u.Points : 0));
            //Ejercicio 16: Dada una lista de productos, suma los precios de aquellos que tengan el precio máximo registrado.
            var products3 = new List<Product>
            {
                new Product { Name = "TV", Price = 500 },
                new Product { Name = "Tablet", Price = 300 },
                new Product { Name = "Laptop", Price = 500 }
            };
            double maxPrice = products3.Max(p => p.Price);
            Console.WriteLine(products3.Sum(p => p.Price == maxPrice ? p.Price : 0));
        }
}
}
