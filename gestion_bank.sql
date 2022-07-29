PGDMP     .    3    
            z           gestion_bank    13.0    13.0     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    24895    gestion_bank    DATABASE     h   CREATE DATABASE gestion_bank WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'French_France.1252';
    DROP DATABASE gestion_bank;
                postgres    false            �            1259    24896    client    TABLE     �   CREATE TABLE public.client (
    num_cpte character varying(50) NOT NULL,
    nom_cli character varying(255),
    solde integer NOT NULL
);
    DROP TABLE public.client;
       public         heap    postgres    false            �            1259    24903    retrait    TABLE     �   CREATE TABLE public.retrait (
    id_retrait bigint NOT NULL,
    date_retrait date,
    montant_rettrait integer NOT NULL,
    num_check character varying(255),
    num_cpte character varying(50)
);
    DROP TABLE public.retrait;
       public         heap    postgres    false            �            1259    24901    retrait_id_retrait_seq    SEQUENCE        CREATE SEQUENCE public.retrait_id_retrait_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.retrait_id_retrait_seq;
       public          postgres    false    202            �           0    0    retrait_id_retrait_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.retrait_id_retrait_seq OWNED BY public.retrait.id_retrait;
          public          postgres    false    201            �            1259    24911 	   versement    TABLE     �   CREATE TABLE public.versement (
    id_vers bigint NOT NULL,
    date_vers date,
    monta_vers integer NOT NULL,
    num_cpte character varying(50)
);
    DROP TABLE public.versement;
       public         heap    postgres    false            �            1259    24909    versement_id_vers_seq    SEQUENCE     ~   CREATE SEQUENCE public.versement_id_vers_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.versement_id_vers_seq;
       public          postgres    false    204            �           0    0    versement_id_vers_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.versement_id_vers_seq OWNED BY public.versement.id_vers;
          public          postgres    false    203            ,           2604    24906    retrait id_retrait    DEFAULT     x   ALTER TABLE ONLY public.retrait ALTER COLUMN id_retrait SET DEFAULT nextval('public.retrait_id_retrait_seq'::regclass);
 A   ALTER TABLE public.retrait ALTER COLUMN id_retrait DROP DEFAULT;
       public          postgres    false    201    202    202            -           2604    24914    versement id_vers    DEFAULT     v   ALTER TABLE ONLY public.versement ALTER COLUMN id_vers SET DEFAULT nextval('public.versement_id_vers_seq'::regclass);
 @   ALTER TABLE public.versement ALTER COLUMN id_vers DROP DEFAULT;
       public          postgres    false    203    204    204            �          0    24896    client 
   TABLE DATA           :   COPY public.client (num_cpte, nom_cli, solde) FROM stdin;
    public          postgres    false    200   1       �          0    24903    retrait 
   TABLE DATA           b   COPY public.retrait (id_retrait, date_retrait, montant_rettrait, num_check, num_cpte) FROM stdin;
    public          postgres    false    202   �       �          0    24911 	   versement 
   TABLE DATA           M   COPY public.versement (id_vers, date_vers, monta_vers, num_cpte) FROM stdin;
    public          postgres    false    204   =       �           0    0    retrait_id_retrait_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.retrait_id_retrait_seq', 12, true);
          public          postgres    false    201            �           0    0    versement_id_vers_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.versement_id_vers_seq', 13, true);
          public          postgres    false    203            /           2606    24900    client client_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_pkey PRIMARY KEY (num_cpte);
 <   ALTER TABLE ONLY public.client DROP CONSTRAINT client_pkey;
       public            postgres    false    200            1           2606    24908    retrait retrait_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.retrait
    ADD CONSTRAINT retrait_pkey PRIMARY KEY (id_retrait);
 >   ALTER TABLE ONLY public.retrait DROP CONSTRAINT retrait_pkey;
       public            postgres    false    202            3           2606    24916    versement versement_pkey 
   CONSTRAINT     [   ALTER TABLE ONLY public.versement
    ADD CONSTRAINT versement_pkey PRIMARY KEY (id_vers);
 B   ALTER TABLE ONLY public.versement DROP CONSTRAINT versement_pkey;
       public            postgres    false    204            4           2606    24917 #   retrait fk1xkpp4ly8lnw5y40g3vlo36in    FK CONSTRAINT     �   ALTER TABLE ONLY public.retrait
    ADD CONSTRAINT fk1xkpp4ly8lnw5y40g3vlo36in FOREIGN KEY (num_cpte) REFERENCES public.client(num_cpte);
 M   ALTER TABLE ONLY public.retrait DROP CONSTRAINT fk1xkpp4ly8lnw5y40g3vlo36in;
       public          postgres    false    202    2863    200            5           2606    24922 %   versement fkg25pqbk00flccuo9u0m2isqa5    FK CONSTRAINT     �   ALTER TABLE ONLY public.versement
    ADD CONSTRAINT fkg25pqbk00flccuo9u0m2isqa5 FOREIGN KEY (num_cpte) REFERENCES public.client(num_cpte);
 O   ALTER TABLE ONLY public.versement DROP CONSTRAINT fkg25pqbk00flccuo9u0m2isqa5;
       public          postgres    false    204    200    2863            �   �   x�M���0���S�	PB)�cՁ2�J ��b�J����'A�y�����Uk���z��������<w��+���LQ��F��A7<JG;z����"�>�I�<,����H�:Ú���(�uok��.!KѯK��Q�>=W�Q&J�'*�5>      �   U   x�m˱�0���K��?!��(h��"�paY:9��YP,u��z�R��6�7����Q>�����ޜ㞻'��}nN�9� ?D�      �   O   x�Mɱ�0�ڿK���CR3 �� A�\{�ɥ�*�F�y�m��!�5ZjL��W�����A�asL�N�:���x     