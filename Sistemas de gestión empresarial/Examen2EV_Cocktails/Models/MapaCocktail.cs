using System;
using System.Collections.Generic;
using System.Text;

namespace danielAlonso2Eval.Models
{

    public class MapaCocktail
    {
        public int id { get; set; }
        public string name { get; set; }
        public string category { get; set; }
        public string alcoholic { get; set; }
        public string glass { get; set; }
        public string instructions { get; set; }
        public float latitud { get; set; }
        public float longitud { get; set; }
    }

}
