namespace FrontBack.Client.Modelos
{
    public class Post
    {
        public int Id { get; set; }
        public string Slug { get; set; } = string.Empty;
        public string Title { get; set; } = string.Empty;
        public string Content { get; set; } = string.Empty;
        public string Image { get; set; } = string.Empty;
        public string Category { get; set; } = string.Empty;
        public string PublishedAt { get; set; } = string.Empty;

    }
}
