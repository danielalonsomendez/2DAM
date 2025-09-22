using System.ComponentModel.DataAnnotations;
using System.Xml.Linq;
using System.ComponentModel.DataAnnotations;
using System.Xml.Linq;

namespace Any01
{
    internal class Program
    {
        static void Main(string[] args)
        {
            var numbers = new[] { 5, 9, 2, 12, 6 };
            bool isAnyLargerThan10 = numbers.Any(n => n > 10);
            Console.WriteLine(isAnyLargerThan10);

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

            // Let's check if there is any pet in this collection that is named Bruce.
            var isAnyPetNamedBruce = pets.Any(p => p.Name == "Bruce");
            Console.WriteLine(isAnyPetNamedBruce);

            // Let's check if there is any fish in this collection.
            var isAnyFish = pets.Any(p => p.PetType == PetType.Fish);
            Console.WriteLine(isAnyFish);

            // Let's check if there is any pet whose name is longer than six letters
            // and whose ID is an even number.
            var isAnyPetWithLongNameAndEvenId = pets.Any(p => p.Name.Length > 6 && p.Id % 2 == 0);
            Console.WriteLine(isAnyPetWithLongNameAndEvenId);

            // Let’s check if the collection is not empty.
            var isNotEmpty = pets.Any();
            Console.WriteLine(isNotEmpty);

            // Check if in the collection of Pets there is a cat that weighs over 2 kilos.
            var isAnyCatOver2Kilos = pets.Any(p => p.PetType == PetType.Cat && p.Weight > 2);
            Console.WriteLine(isAnyCatOver2Kilos);

            // Si el primer carácter de un nombre es minúscula o si la long < 2 o si la long > 25 la función devuelve FALSE.
            var areAllNamesValid = !pets.Any(p =>
                char.IsLower(p.Name[0]) || p.Name.Length < 2 || p.Name.Length > 25);

            // EJ 1: Verificar si hay algún número negativo
            int[] numeros = { 5, -3, 8, 0 };
            var hayNegativos = numeros.Any(n => n < 0);
            Console.WriteLine(hayNegativos);

            // EJ 2: Comprobar si hay algún libro sin autor
            List<Libro> libros = new List<Libro>
            {
                new Libro { Titulo = "C#", Autor = "Juan" },
                new Libro { Titulo = "LINQ", Autor = "" }
            };
            var hayLibrosSinAutor = libros.Any(l => string.IsNullOrEmpty(l.Autor));
            Console.WriteLine(hayLibrosSinAutor);

            // EJ 3: Verificar si hay menores de edad
            List<Persona> personas = new List<Persona>
            {
                new Persona { Nombre = "Ana", Edad = 25 },
                new Persona { Nombre = "Luis", Edad = 16 }
            };
            var hayMenoresDeEdad = personas.Any(p => p.Edad < 18);
            Console.WriteLine(hayMenoresDeEdad);

            // EJ 4: Comprobar si hay productos agotados
            List<Producto> productos = new List<Producto>
            {
                new Producto { Nombre = "Teclado", Stock = 10 },
                new Producto { Nombre = "Ratón", Stock = 0 }
            };
            var hayProductosAgotados = productos.Any(p => p.Stock == 0);
            Console.WriteLine(hayProductosAgotados);

            // EJ 5: Verificar si hay cadenas vacías
            string[] cadenas = { "hola", "", "mundo" };
            var hayCadenasVacias = cadenas.Any(s => string.IsNullOrEmpty(s));
            Console.WriteLine(hayCadenasVacias);

            // EJ 6: Comprobar si algún estudiante tiene nota mayor a 9
            List<Estudiante> estudiantes = new List<Estudiante>
            {
                new Estudiante { Nombre = "Carlos", Nota = 8.5 },
                new Estudiante { Nombre = "Lucía", Nota = 9.5 }
            };
            var hayMayora9 = estudiantes.Any(e => e.Nota > 9);
            Console.WriteLine(hayMayora9);

            // EJ 7: Verificar si hay facturas impagas
            List<Factura> facturas = new List<Factura>
            {
                new Factura { Id = 1, Pagada = true },
                new Factura { Id = 2, Pagada = false }
            };
            var hayFacturasImpagadas = facturas.Any(f => !f.Pagada);
            Console.WriteLine(hayFacturasImpagadas);

            // EJ 8: Comprobar si hay palabras que empiecen por vocal
            string[] palabras = { "sol", "estrella", "universo", "agua" };
            var hayPalabrasConVocal = palabras.Any(p => "aeiouAEIOU".Contains(p[0]));
            Console.WriteLine(hayPalabrasConVocal);

            // EJ 9: Verificar si hay algún número par mayor que 100
            int[] numeros2 = { 45, 102, 88, 150 };
            var hayParMayor100 = numeros2.Any(n => n > 100 && n % 2 == 0);
            Console.WriteLine(hayParMayor100);

            // EJ 10: Comprobar si hay empleados con salario superior a 3000
            List<Empleado> empleados = new List<Empleado>
            {
                new Empleado { Nombre = "Marta", Salario = 2800 },
                new Empleado { Nombre = "Jorge", Salario = 3200 }
            };
            var haySalarioSuperior3000 = empleados.Any(e => e.Salario > 3000);
            Console.WriteLine(haySalarioSuperior3000);
        }

        class Factura { public int Id; public bool Pagada; }
        class Empleado { public string Nombre; public double Salario; }
        class Libro { public string Titulo; public string Autor; }
        class Persona { public string Nombre; public int Edad; }
        class Producto { public string Nombre; public int Stock; }
        class Estudiante { public string Nombre; public double Nota; }
    }
}