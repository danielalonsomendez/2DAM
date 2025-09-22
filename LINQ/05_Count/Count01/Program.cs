namespace Count01
{
    internal class Program
    {
        static void Main(string[] args)
        {
            // EJ 1: ¿Cuántos números son pares?
            int[] numeros = { 2, 3, 4, 5, 6 };
            var cantidadPares = numeros.Count(n => n % 2 == 0);
            Console.WriteLine(cantidadPares);
            // EJ 2: ¿Cuántos libros tienen más de 300 páginas?

        List<Libro> libros = new List<Libro>
{
    new Libro { Titulo = "C#", Paginas = 250 },
    new Libro { Titulo = "LINQ", Paginas = 320 },
    new Libro { Titulo = "ASP.NET", Paginas = 400 }
};
            var cantidadLibros = libros.Count(l => l.Paginas > 300);
            Console.WriteLine(cantidadLibros);


            // EJ 3: ¿Cuántas personas son menores de edad?
            List<Persona> personas = new List<Persona>
{
    new Persona { Nombre = "Ana", Edad = 25 },
    new Persona { Nombre = "Luis", Edad = 16 },
    new Persona { Nombre = "Marta", Edad = 17 }
};
var cantidadMenores = personas.Count(p => p.Edad < 18);
            Console.WriteLine(cantidadMenores);

            // EJ 4: ¿Cuántos productos están agotados?

            List<Producto> productos = new List<Producto>
{
    new Producto { Nombre = "Teclado", Stock = 10 },
    new Producto { Nombre = "Ratón", Stock = 0 },
    new Producto { Nombre = "Monitor", Stock = 0 }
};

      var cantidadAgotados = productos.Count(p => p.Stock == 0);
            Console.WriteLine(cantidadAgotados);

            // EJ 5: ¿Cuántas cadenas tienen más de 5 caracteres?
            string[] cadenas = { "hola", "mundo", "programación", "LINQ" };
var cantidadCadenas = cadenas.Count(c => c.Length > 5);
            Console.WriteLine(cantidadCadenas);
            // EJ 6: ¿Cuántos estudiantes tienen nota superior a 8?

            List<Estudiante> estudiantes = new List<Estudiante>
{
    new Estudiante { Nombre = "Carlos", Nota = 7.5 },
    new Estudiante { Nombre = "Lucía", Nota = 9.0 },
    new Estudiante { Nombre = "Pedro", Nota = 8.5 }
};

        var cantidadEstudiantes = estudiantes.Count(e => e.Nota > 8);
            Console.WriteLine(cantidadEstudiantes);

            // EJ 7: ¿Cuántas facturas están impagas?

            List<Factura> facturas = new List<Factura>
{
    new Factura { Id = 1, Pagada = true },
    new Factura { Id = 2, Pagada = false },
    new Factura { Id = 3, Pagada = false }
};

     var cantidadImpagas = facturas.Count(f => !f.Pagada);
            Console.WriteLine(cantidadImpagas);

            // EJ 8: ¿Cuántas palabras empiezan por vocal?
            string[] palabras = { "sol", "estrella", "universo", "agua", "idea" };
var cantidadVocal = palabras.Count(p => "aeiouAEIOU".Contains(p[0]));
            Console.WriteLine(cantidadVocal);

            // EJ 9: ¿Cuántos números son mayores que 100?
            int[] numeros2 = { 45, 102, 88, 150, 200 };

        var cantidadMayores = numeros2.Count(n => n > 100);
            Console.WriteLine(cantidadMayores);

            // EJ 10: ¿Cuántos empleados ganan menos de 1200€?

            List<Empleado> empleados = new List<Empleado>
{
    new Empleado { Nombre = "Marta", Salario = 1100 },
    new Empleado { Nombre = "Jorge", Salario = 1500 },
    new Empleado { Nombre = "Laura", Salario = 1000 }
};

      var cantidadEmpleados = empleados.Count(e => e.Salario < 1200);
            Console.WriteLine(cantidadEmpleados);

        }
        class Persona { public string Nombre; public int Edad; }
        class Libro { public string Titulo; public int Paginas; }
        class Producto { public string Nombre; public int Stock; }
        class Estudiante { public string Nombre; public double Nota; }
        class Empleado { public string Nombre; public double Salario; }
        class Factura { public int Id; public bool Pagada; }
    }
}
