namespace OrderByEjer
{
    internal class Program
    {
        static void Main(string[] args)
        {
            // 1. Dado un array de nombres, ordénalos alfabéticamente y verifica si alguno contiene la letra "a".
            string[] nombres = { "Carlos", "Ana", "Luis", "Bea" };
            nombres.OrderBy(n => n).ToList();
            foreach (var nombre in nombres)
            {
                    Console.WriteLine(nombre);
            }
            bool contieneA = nombres.Any(n => n.Contains("a") || n.Contains("A"));
            Console.WriteLine(contieneA);
            //2. Dado un array de edades, ordénalas y cuenta cuántas son mayores de 30.
            int[] edades = { 25, 40, 18, 35, 60 };
            int[] edadesOrdenado = edades.OrderBy(e => e).ToArray();
            int[] edadesMayor40 = edadesOrdenado.Where(e => e > 30).ToArray();
            Console.WriteLine(String.Join(", ", edadesOrdenado));
            Console.WriteLine(String.Join(", ", edadesMayor40));
            //3. Ordenar productos por precio y verificar si todos cuestan más de 10
        Producto[] productos = {
    new Producto { Nombre = "Pan", Precio = 1.5 },
    new Producto { Nombre = "Queso", Precio = 12 },
    new Producto { Nombre = "Vino", Precio = 20 }
};
            Console.WriteLine(productos.OrderBy(producto => producto.Precio));
            Console.WriteLine(!productos.Any(producto => producto.Precio < 10));
            // 4. Ordenar ciudades por longitud de nombre y verificar si alguna contiene "Madrid"
            string[] ciudades = { "Bilbao", "Madrid", "Barcelona", "Sevilla" };
            Console.WriteLine(String.Join(", ", ciudades.OrderBy(ciudad => ciudad.Length)));
            Console.WriteLine(ciudades.Any(ciudad => ciudad.Contains("Madrid")));
            // 5. Ordenar números y verificar si todos son pares
            int[] numeros = { 2, 4, 6, 8, 10 };
            Console.WriteLine(String.Join(", ", numeros.OrderBy(n => n)));
            Console.WriteLine(numeros.All(n => n % 2 == 0));
            // 6. Ordenar empleados por nombre y contar cuántos tienen más de 5 letras.
        Empleado[] empleados = {
    new Empleado { Nombre = "Ana" },
    new Empleado { Nombre = "Roberto" },
    new Empleado { Nombre = "Luis" }
};
            Console.WriteLine(String.Join(", ", empleados.OrderBy(empleado => empleado.Nombre)));
            Console.WriteLine(empleados.Count(empleado => empleado.Nombre.Length > 5));
            // 7. Ordena las palabras alfabéticamente y verifica si alguna contiene la letra "z".
            string[] palabras = { "sol", "luz", "cielo", "estrella" };
            Console.WriteLine(String.Join(", ", palabras.OrderBy(palabra => palabra)));
            Console.WriteLine(palabras.Any(palabra => palabra.Contains("z")));
            // 8. Ordenar temperaturas y verificar si todas son mayores que 0
            double[] temperaturas = { 15.5, 22.3, 0.0, 18.7 };
            Console.WriteLine(String.Join(", ", temperaturas.OrderBy(temp => temp)));
            Console.WriteLine(temperaturas.All(temp => temp > 0));
            // 9. Ordenar libros por título y verificar si alguno contiene "C#"
        Libro[] libros = {
    new Libro { Titulo = "Aprende Java" },
    new Libro { Titulo = "C# Básico" },
    new Libro { Titulo = "Python Avanzado" }
};
            Console.WriteLine(String.Join(", ", libros.OrderBy(libro => libro.Titulo)));
            Console.WriteLine(libros.Any(libro => libro.Titulo.Contains("C#")));
            //10.Dado un array de estudiantes, ordénalos por nota y cuenta cuántos tienen más de 8.
        Estudiante[] estudiantes = {
    new Estudiante { Nombre = "Lucía", Nota = 9.2 },
    new Estudiante { Nombre = "Pedro", Nota = 7.5 },
    new Estudiante { Nombre = "María", Nota = 8.6 }
};
            var estudiantesOrdenados = estudiantes.OrderBy(est => est.Nota).ToArray();
            var estudiantesMayor8 = estudiantesOrdenados.Count(est => est.Nota > 8);
            foreach (var estudiante in estudiantesOrdenados)
            {
                Console.WriteLine($"{estudiante.Nombre}: {estudiante.Nota}");
            }
            Console.WriteLine(estudiantesMayor8);




        }
        class Estudiante
        {
            public string Nombre { get; set; }
            public double Nota { get; set; }
        }
        class Libro
        {
            public string Titulo { get; set; }
        }
        class Producto
        {
            public string Nombre { get; set; }
            public double Precio { get; set; }
        }
    }
    class Empleado
    {
        public string Nombre { get; set; }
    }
}
