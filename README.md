## Product App
React と Supabase を使用した、シンプルかつ高機能な商品管理アプリケーションです。

## 主な機能
商品一覧表示: 登録された商品をカード形式またはリスト形式で閲覧。

商品管理 (CRUD): 商品の追加、編集、削除機能。

在庫・価格管理: データベースと連携したリアルタイムな情報更新。

レスポンシブ UI: Tailwind CSS を採用した、PC・スマホ両対応のデザイン。

型安全: TypeScript による堅牢な開発。

## 使用技術
フロントエンド: React (Vite), TypeScript

スタイリング: Tailwind CSS

バックエンド/DB: Supabase (PostgreSQL)

アイコン: Lucide React

## セットアップ
1. リポジトリのクローン
Bash
git clone https://github.com/aokuma89/product_app.git
cd product_app
2. パッケージのインストール
Bash
npm install
3. 環境変数の設定
.env ファイルを作成し、Supabase の接続情報を設定してください。

コード スニペット
VITE_SUPABASE_URL=あなたの_SUPABASE_URL
VITE_SUPABASE_ANON_KEY=あなたの_SUPABASE_ANON_KEY
4. データベースの構築
Supabase の SQL Editor で以下のテーブルを作成してください。

## SQL
create table products (
  id uuid default uuid_generate_v4() primary key,
  name text not null,
  description text,
  price integer not null,
  stock integer default 0,
  image_url text,
  created_at timestamp with time zone default now()
);

-- RLS設定 (必要に応じて)
alter table products enable row level security;
create policy "Allow public read access" on products for select using (true);
5. 開発サーバーの起動
Bash
npm run dev
## 今後のロードマップ
[ ] 商品画像のアップロード機能 (Supabase Storage)

[ ] カテゴリ別のフィルタリング機能

[ ] 注文管理システムとの連携


※これは学校の課題で制作したアプリです
