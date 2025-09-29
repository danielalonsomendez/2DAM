using System.Drawing;

namespace MinMaxEjer
{
    internal class Program
    {
        static void Main(string[] args)
        {
            // Ejercicio 1: Dado un array de enteros, encuentra el valor mínimo y máximo. Además, verifica si hay algún número mayor a 50.

            int[] numeros = { 23, 45, 12, 67, 34, 89, 2 };

            Console.WriteLine(numeros.Min());
            Console.WriteLine(numeros.Max());
            Console.WriteLine(numeros.Any(n => n > 50));
            // Ejercicio 2: Dada una lista de nombres, encuentra el nombre más corto y el más largo. Verifica si todos los nombres contienen la letra 'a' o la letra ‘A’.
            List<string> nombres = new List<string> { "Ana", "José", "María", "Pedro", "Luisa" };
            Console.WriteLine(nombres.Min(n => n.Length));
            Console.WriteLine(nombres.Max(n => n.Length));
            Console.WriteLine(nombres.All(n => n.Contains('a') || n.Contains('A')));
            // Ejercicio 3:  Define una clase Persona con propiedades Nombre y Edad. Dada una lista de personas, encuentra la persona con la edad mínima y máxima. Cuenta cuántas personas son mayores de 30. 

            List<Persona> personas = new List<Persona>
{
    new Persona { Nombre = "Carlos", Edad = 25 },
    new Persona { Nombre = "Lucía", Edad = 32 },
    new Persona { Nombre = "Pedro", Edad = 29 },
    new Persona { Nombre = "María", Edad = 45 }
};
            Console.WriteLine(personas.Min(p => p.Edad));
            Console.WriteLine(personas.Max(p => p.Edad));
            Console.WriteLine(personas.Count(p => p.Edad > 30));
            // Ejercicio 4: Dado un array de números reales (double), encuentra el valor mínimo y máximo. Verifica si existe algún número negativo.
            double[] valores = { -3.5, 2.7, 0, 5.8, -1.2, 7.1 };
            Console.WriteLine(valores.Min());
            Console.WriteLine(valores.Max());
            Console.WriteLine(valores.Any(v => v < 0));
            // Ejercicio 5: Dado un array de enteros, encuentra el mínimo y máximo, y crea una lista ordenada ascendentemente de los números que sean mayores que el mínimo.
            int[] numeros2 = { 10, 5, 15, 3, 20, 7 };
            Console.WriteLine(numeros2.Min());
            Console.WriteLine(numeros2.Max());
            List<int> mayoresQueMinimo = numeros2.Where(n => n > numeros2.Min()).OrderBy(n => n).ToList();
            Console.WriteLine(string.Join(", ", mayoresQueMinimo));
            // Ejercicio 6: Define una clase Producto con propiedades Nombre y Precio. Dada una lista de productos, encuentra el precio mínimo y máximo. Verifica si algún producto cuesta menos de 10.

        List<Producto> productos = new List<Producto>
{
    new Producto { Nombre = "Camiseta", Precio = 15.5m },
    new Producto { Nombre = "Pantalón", Precio = 35m },
    new Producto { Nombre = "Zapatos", Precio = 50m },
    new Producto { Nombre = "Calcetines", Precio = 5m }
};
            Console.WriteLine(productos.Min(p => p.Precio));
            Console.WriteLine(productos.Max(p => p.Precio));
            Console.WriteLine(productos.Any(p => p.Precio < 10));
            //Ejercicio 7: Dado un array de enteros, encuentra el mínimo y máximo.Además, cuenta cuántos números son pares.
            int[] numeros3 = { 4, 7, 10, 3, 8, 2 };
            Console.WriteLine(numeros3.Min());
            Console.WriteLine(numeros3.Max());
            Console.WriteLine(numeros3.Count(n => n % 2 == 0));

            //Ejercicio 8: Dado un listado de enteros, encuentra el valor mínimo y máximo. Verifica si todos los números son positivos.
            List<int> numeros4 = new List<int> { 1, 5, 10, 12, 7 };
            Console.WriteLine(numeros4.Min());
            Console.WriteLine(numeros4.Max());
            Console.WriteLine(numeros4.All(n => n > 0));

            //Ejercicio 9: Dado un array de enteros, verifica si todos los valores son mayores que el mínimo.
            int[] valores2 = { 5, 8, 12, 5 };
            Console.WriteLine(valores2.All(v => v > valores2.Min()));


            //Ejercicio 10: Dada una lista de números, filtra los pares y encuentra el menor entre ellos.
            List<int> numeros5 = new List<int> { 4, 7, 10, 3, 8 };
            Console.WriteLine(numeros5.Where(n => n % 2 == 0).Min());


        }
        class Producto
        {
            public string Nombre { get; set; }
            public decimal Precio { get; set; }
        }
        class Persona
        {
            public string Nombre { get; set; }
            public int Edad { get; set; }
        }
    }
}
