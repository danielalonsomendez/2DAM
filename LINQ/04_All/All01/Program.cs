namespace All01
{
    internal class Program
    {
        static void Main(string[] args)
        {
            // EJ 1:¿Todos los números son positivos?
            int[] numeros = { 3, 5, 7, 9 };
            bool todosPositivos = numeros.All(n => n > 0);
            Console.WriteLine(todosPositivos);
            // EJ 2:¿Todos los libros tienen autor?
            List<Libro> libros = new List<Libro>
{
    new Libro { Titulo = "C#", Autor = "Juan" },
    new Libro { Titulo = "LINQ", Autor = "Ana" }
};

      var todosTienenAutor = libros.All(l => !string.IsNullOrEmpty(l.Autor));
            Console.WriteLine(todosTienenAutor);
            // EJ 3:¿Todos son mayores de edad?

            List<Persona> personas = new List<Persona>
{
    new Persona { Nombre = "Ana", Edad = 25 },
    new Persona { Nombre = "Luis", Edad = 19 }
};
            var todosMayoresDeEdad = personas.All(p => p.Edad >= 18);   
            Console.WriteLine(todosMayoresDeEdad);
            // EJ 4:¿Todos los productos están en stock?
            List<Producto> productos = new List<Producto>
{
    new Producto { Nombre = "Teclado", Stock = 10 },
    new Producto { Nombre = "Ratón", Stock = 5 }
};
            var todosEnStock = productos.All(p => p.Stock > 0);
            Console.WriteLine(todosEnStock);
            // EJ 5: ¿Todas las cadenas tienen más de 3 caracteres?
            string[] cadenas = { "hola", "mundo", "LINQ" };
            var todasMasDeTres = cadenas.All(c => c.Length > 3);
            Console.WriteLine(todasMasDeTres);
            // EJ 6:¿Todos los estudiantes aprobaron el examen?
            List<Estudiante> estudiantes = new List<Estudiante>
{
    new Estudiante { Nombre = "Carlos", Nota = 6.5 },
    new Estudiante { Nombre = "Lucía", Nota = 7.0 }
};
            var todosAprobaron = estudiantes.All(e => e.Nota >= 5.0);
            Console.WriteLine(todosAprobaron);
            // EJ 7: ¿Todas las facturas están pagadas?
            List<Factura> facturas = new List<Factura>
{
    new Factura { Id = 1, Pagada = true },
    new Factura { Id = 2, Pagada = true }
};
            var todasPagadas = facturas.All(f => f.Pagada);
            Console.WriteLine(todasPagadas);
            // EJ 8:¿Todas las palabras empiezan por mayúscula?
            string[] palabras = { "Hola", "Mundo", "Programación" };
            var todasEmpiezanMayuscula = palabras.All(p => char.IsUpper(p[0]));
            Console.WriteLine(todasEmpiezanMayuscula);
            // EJ 9:¿Todos los números son pares?
            int[] numeros2 = { 2, 4, 6, 8 };
           var todosPares = numeros2.All(n => n % 2 == 0);
            Console.WriteLine(todosPares);
            // EJ 10:¿Todos los empleados ganan más de 1000€?

        List<Empleado> empleados = new List<Empleado>
{
    new Empleado { Nombre = "Marta", Salario = 1200 },
    new Empleado { Nombre = "Jorge", Salario = 1500 }
};
            var todosGananMasDeMil = empleados.All(e => e.Salario > 1000);
            Console.WriteLine(todosGananMasDeMil);

        }
    class Libro { public string Titulo; public string Autor; }
        class Persona { public string Nombre; public int Edad; }
        class Producto { public string Nombre; public int Stock; }
        class Estudiante { public string Nombre; public double Nota; }
        class Factura { public int Id; public bool Pagada; }
        class Empleado { public string Nombre; public double Salario; }

    }
}
